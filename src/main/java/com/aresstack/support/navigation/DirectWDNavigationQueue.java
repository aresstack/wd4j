package com.aresstack.support.navigation;

import java.util.concurrent.Callable;

public class DirectWDNavigationQueue implements WDNavigationQueue {

    @Override
    public <T> T submit(String contextId, Callable<T> navigationCommand) {
        try {
            return navigationCommand.call();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Navigation command failed.", e);
        }
    }

    @Override
    public void discard(String contextId) {
        // Keep this method for drop-in compatibility with queued implementations.
    }

    @Override
    public void shutdown() {
        // Keep this method for drop-in compatibility with queued implementations.
    }
}
