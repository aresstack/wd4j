package com.aresstack.support.navigation;

public final class WDNavigationQueues {

    public static final String ENABLED_PROPERTY = "wd4j.navigation.queue.enabled";
    public static final String QUIET_PERIOD_MILLIS_PROPERTY = "wd4j.navigation.queue.quietPeriodMs";

    private WDNavigationQueues() {
    }

    public static WDNavigationQueue fromSystemProperties() {
        if (!isQueueEnabled()) {
            return new DirectWDNavigationQueue();
        }
        return new SerialWDNavigationQueue(getQuietPeriodMillis());
    }

    public static WDNavigationQueue serial(long quietPeriodMillis) {
        return new SerialWDNavigationQueue(quietPeriodMillis);
    }

    public static WDNavigationQueue direct() {
        return new DirectWDNavigationQueue();
    }

    private static boolean isQueueEnabled() {
        return Boolean.parseBoolean(System.getProperty(ENABLED_PROPERTY, "true"));
    }

    private static long getQuietPeriodMillis() {
        String configuredValue = System.getProperty(QUIET_PERIOD_MILLIS_PROPERTY, "0");
        try {
            long quietPeriodMillis = Long.parseLong(configuredValue.trim());
            if (quietPeriodMillis < 0L) {
                return 0L;
            }
            return quietPeriodMillis;
        } catch (NumberFormatException e) {
            return 0L;
        }
    }
}
