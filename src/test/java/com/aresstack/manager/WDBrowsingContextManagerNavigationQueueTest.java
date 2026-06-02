package com.aresstack.manager;

import com.aresstack.api.WDCommand;
import com.aresstack.api.WDCommandResponse;
import com.aresstack.api.WDWebSocketManager;
import com.aresstack.command.response.WDBrowsingContextResult;
import com.aresstack.service.WDEventDispatcher;
import com.aresstack.support.navigation.WDNavigationQueue;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WDBrowsingContextManagerNavigationQueueTest {

    @Test
    public void shouldRouteNavigationCommandsThroughNavigationQueue() {
        RecordingWebSocketManager webSocketManager = new RecordingWebSocketManager();
        RecordingNavigationQueue navigationQueue = new RecordingNavigationQueue();
        WDBrowsingContextManager manager = new WDBrowsingContextManager(webSocketManager, navigationQueue);

        WDBrowsingContextResult.NavigateResult result = manager.navigate("https://example.test", "context-1");

        assertEquals("context-1", navigationQueue.getLastContextId());
        assertEquals(1, navigationQueue.getSubmissions());
        assertEquals("browsingContext.navigate", webSocketManager.getLastCommand().getName());
        assertEquals("https://example.test", result.getUrl());
    }

    private static class RecordingNavigationQueue implements WDNavigationQueue {
        private final AtomicInteger submissions = new AtomicInteger();
        private String lastContextId;

        @Override
        public <T> T submit(String contextId, Callable<T> navigationCommand) {
            lastContextId = contextId;
            submissions.incrementAndGet();
            try {
                return navigationCommand.call();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void discard(String contextId) {
        }

        @Override
        public void shutdown() {
        }

        String getLastContextId() {
            return lastContextId;
        }

        int getSubmissions() {
            return submissions.get();
        }
    }

    private static class RecordingWebSocketManager implements WDWebSocketManager {
        private WDCommand lastCommand;

        @Override
        @SuppressWarnings("unchecked")
        public <T> T sendAndWaitForResponse(WDCommand command, Type responseType) {
            lastCommand = command;
            return (T) new WDBrowsingContextResult.NavigateResult("navigation-1", "https://example.test");
        }

        @Override
        public void registerEventListener(WDEventDispatcher eventDispatcher) {
        }

        @Override
        public void sendFireAndForget(WDCommand command) {
            lastCommand = command;
        }

        @Override
        public boolean isConnected() {
            return true;
        }

        @Override
        public void shutdown() {
        }

        WDCommand getLastCommand() {
            return lastCommand;
        }
    }
}
