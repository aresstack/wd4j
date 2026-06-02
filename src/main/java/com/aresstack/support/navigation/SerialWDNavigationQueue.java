package com.aresstack.support.navigation;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReentrantLock;

public class SerialWDNavigationQueue implements WDNavigationQueue {

    private final Map<String, NavigationLane> lanes = new HashMap<String, NavigationLane>();
    private final Object laneMonitor = new Object();
    private final long quietPeriodMillis;

    public SerialWDNavigationQueue() {
        this(0L);
    }

    public SerialWDNavigationQueue(long quietPeriodMillis) {
        if (quietPeriodMillis < 0L) {
            throw new IllegalArgumentException("quietPeriodMillis must not be negative.");
        }
        this.quietPeriodMillis = quietPeriodMillis;
    }

    @Override
    public <T> T submit(String contextId, Callable<T> navigationCommand) {
        String laneId = requireContextId(contextId);
        NavigationLane lane = retainLane(laneId);

        lane.lock();
        try {
            return callNavigationCommand(navigationCommand);
        } finally {
            waitForQuietPeriod();
            lane.unlock();
            releaseLane(laneId, lane);
        }
    }

    @Override
    public void discard(String contextId) {
        if (contextId == null || contextId.trim().isEmpty()) {
            return;
        }
        synchronized (laneMonitor) {
            NavigationLane lane = lanes.get(contextId);
            if (lane == null) {
                return;
            }
            lane.discard();
            removeLaneIfUnused(contextId, lane);
        }
    }

    @Override
    public void shutdown() {
        synchronized (laneMonitor) {
            lanes.clear();
        }
    }

    private NavigationLane retainLane(String contextId) {
        synchronized (laneMonitor) {
            NavigationLane lane = lanes.get(contextId);
            if (lane == null) {
                lane = new NavigationLane();
                lanes.put(contextId, lane);
            }
            lane.retain();
            return lane;
        }
    }

    private void releaseLane(String contextId, NavigationLane lane) {
        synchronized (laneMonitor) {
            lane.release();
            removeLaneIfUnused(contextId, lane);
        }
    }

    private void removeLaneIfUnused(String contextId, NavigationLane lane) {
        if (lane.canBeRemoved()) {
            lanes.remove(contextId);
        }
    }

    private String requireContextId(String contextId) {
        if (contextId == null || contextId.trim().isEmpty()) {
            throw new IllegalArgumentException("contextId must not be null or empty.");
        }
        return contextId;
    }

    private <T> T callNavigationCommand(Callable<T> navigationCommand) {
        try {
            return navigationCommand.call();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Navigation command failed.", e);
        }
    }

    private void waitForQuietPeriod() {
        if (quietPeriodMillis <= 0L) {
            return;
        }
        try {
            Thread.sleep(quietPeriodMillis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted while waiting for the navigation quiet period.", e);
        }
    }

    private static class NavigationLane {
        private final ReentrantLock lock = new ReentrantLock(true);
        private int users;
        private boolean discarded;

        void retain() {
            users++;
        }

        void release() {
            users--;
        }

        void discard() {
            discarded = true;
        }

        boolean canBeRemoved() {
            return discarded && users == 0;
        }

        void lock() {
            lock.lock();
        }

        void unlock() {
            lock.unlock();
        }
    }
}
