package com.aresstack.support.navigation;

import java.util.concurrent.Callable;

public interface WDNavigationQueue {

    <T> T submit(String contextId, Callable<T> navigationCommand);

    void discard(String contextId);

    void shutdown();
}
