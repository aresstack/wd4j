package com.aresstack.support.navigation;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SerialWDNavigationQueueTest {

    @Test
    public void shouldSerializeNavigationCommandsForSameContext() throws Exception {
        SerialWDNavigationQueue queue = new SerialWDNavigationQueue();
        ParallelismProbe probe = new ParallelismProbe(50L);

        runConcurrently(8, new NavigationTaskFactory() {
            @Override
            public Callable<Void> createTask() {
                return new Callable<Void>() {
                    @Override
                    public Void call() {
                        return queue.submit("context-1", probe.asCallable());
                    }
                };
            }
        });

        assertEquals(1, probe.getHighestParallelism());
        assertEquals(8, probe.getExecutions());
    }

    @Test
    public void shouldAllowParallelNavigationCommandsForDifferentContexts() throws Exception {
        SerialWDNavigationQueue queue = new SerialWDNavigationQueue();
        ParallelismProbe probe = new ParallelismProbe(120L);

        runConcurrently(6, new NavigationTaskFactory() {
            private final AtomicInteger index = new AtomicInteger();

            @Override
            public Callable<Void> createTask() {
                final String contextId = "context-" + index.incrementAndGet();
                return new Callable<Void>() {
                    @Override
                    public Void call() {
                        return queue.submit(contextId, probe.asCallable());
                    }
                };
            }
        });

        assertTrue(probe.getHighestParallelism() > 1);
        assertEquals(6, probe.getExecutions());
    }

    private void runConcurrently(int taskCount, NavigationTaskFactory taskFactory) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(taskCount, new DaemonThreadFactory());
        try {
            CountDownLatch startSignal = new CountDownLatch(1);
            List<Future<Void>> futures = new ArrayList<Future<Void>>();

            for (int i = 0; i < taskCount; i++) {
                final Callable<Void> task = taskFactory.createTask();
                futures.add(executorService.submit(new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        startSignal.await();
                        return task.call();
                    }
                }));
            }

            startSignal.countDown();

            for (Future<Void> future : futures) {
                future.get(5L, TimeUnit.SECONDS);
            }
        } finally {
            executorService.shutdownNow();
            assertTrue(executorService.awaitTermination(5L, TimeUnit.SECONDS));
        }
    }


    private static class DaemonThreadFactory implements ThreadFactory {
        private final AtomicInteger threadNumber = new AtomicInteger();

        @Override
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "WD4J-navigation-queue-test-" + threadNumber.incrementAndGet());
            thread.setDaemon(true);
            return thread;
        }
    }

    private interface NavigationTaskFactory {
        Callable<Void> createTask();
    }

    private static class ParallelismProbe {
        private final long workMillis;
        private final AtomicInteger activeCommands = new AtomicInteger();
        private final AtomicInteger highestParallelism = new AtomicInteger();
        private final AtomicInteger executions = new AtomicInteger();

        ParallelismProbe(long workMillis) {
            this.workMillis = workMillis;
        }

        Callable<Void> asCallable() {
            return new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    int active = activeCommands.incrementAndGet();
                    executions.incrementAndGet();
                    recordHighestParallelism(active);
                    try {
                        Thread.sleep(workMillis);
                        return null;
                    } finally {
                        activeCommands.decrementAndGet();
                    }
                }
            };
        }

        int getHighestParallelism() {
            return highestParallelism.get();
        }

        int getExecutions() {
            return executions.get();
        }

        private void recordHighestParallelism(int active) {
            while (true) {
                int highest = highestParallelism.get();
                if (active <= highest) {
                    return;
                }
                if (highestParallelism.compareAndSet(highest, active)) {
                    return;
                }
            }
        }
    }
}
