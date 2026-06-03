package com.aresstack.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aresstack.api.markerInterfaces.WDModule;
import com.aresstack.command.request.WDInputRequest;
import com.aresstack.command.request.parameters.input.sourceActions.SourceActions;
import com.aresstack.command.response.WDEmptyResult;
import com.aresstack.type.script.WDRemoteReference;
import com.aresstack.api.WDWebSocketManager;

import java.util.List;

public class WDInputManager implements WDModule {
    private static final Logger LOGGER = LoggerFactory.getLogger(WDInputManager.class);


    private final WDWebSocketManager WDWebSocketManager;

    public WDInputManager(WDWebSocketManager WDWebSocketManager) {
        this.WDWebSocketManager = WDWebSocketManager;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Event Handlers
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Commands
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

     /**
     * Performs a sequence of input actions in the given browsing context.
     *
     * @param contextId The ID of the context where the actions are performed.
     * @param actions   A list of actions to perform.
     * @throws RuntimeException if the action execution fails.
     */
    /**
     * Performs a sequence of input actions in the given browsing context.
     *
     * @param contextId The ID of the context where the actions are performed.
     * @param actions   A list of actions to perform.
     * @throws RuntimeException if the action execution fails.
     */
    public void performActions(String contextId, List<SourceActions> actions) {
        if (actions == null || actions.isEmpty()) {
            throw new IllegalArgumentException("Actions list must not be null or empty.");
        }

        WDWebSocketManager.sendAndWaitForResponse(new WDInputRequest.PerformActions(contextId, actions), WDEmptyResult.class);
        LOGGER.debug("Performed actions in context: {}", contextId);
    }

    /**
     * Releases all input actions for the given browsing context.
     *
     * @param contextId The ID of the context where the actions are released.
     * @throws RuntimeException if the release operation fails.
     */
    public void releaseActions(String contextId) {
        WDWebSocketManager.sendAndWaitForResponse(new WDInputRequest.ReleaseActions(contextId), WDEmptyResult.class);
        LOGGER.debug("Released actions in context: {}", contextId);
    }

    /**
     * Sets files to the given input element.
     *
     * @param contextId       The ID of the context where the element is located.
     * @param sharedReference The shared reference of the element.
     * @param files           A list of file paths to set.
     *
     * @throws RuntimeException if the operation fails.
     */
    public void setFiles(String contextId, WDRemoteReference.SharedReference sharedReference, List<String> files) {
        if (files == null || files.isEmpty()) {
            throw new IllegalArgumentException("File paths list must not be null or empty.");
        }

        WDWebSocketManager.sendAndWaitForResponse(new WDInputRequest.SetFiles(contextId, sharedReference, files), WDEmptyResult.class);
        LOGGER.debug("Files set for element: {}", sharedReference);
    }
}