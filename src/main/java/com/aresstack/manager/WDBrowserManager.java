package com.aresstack.manager;

import com.aresstack.api.markerInterfaces.WDModule;
import com.aresstack.command.request.WDBrowserRequest;
import com.aresstack.command.response.WDBrowserResult;
import com.aresstack.command.response.WDEmptyResult;
import com.aresstack.type.browser.WDClientWindowInfo;
import com.aresstack.type.browser.WDUserContextInfo;
import com.aresstack.api.WDWebSocketManager;

public class WDBrowserManager implements WDModule {

    private final WDWebSocketManager WDWebSocketManager;

    public WDBrowserManager(WDWebSocketManager WDWebSocketManager) {
        this.WDWebSocketManager = WDWebSocketManager;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Event Handlers
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Commands
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Closes the browser.
     */
    public void closeBrowser() {
        WDWebSocketManager.sendAndWaitForResponse(new WDBrowserRequest.Close(), WDEmptyResult.class);
        System.out.println("Browser closed successfully.");
    }

    /**
     * Creates a new user context in the browser aka. a new page in DevTools terminology.
     *
     * @return The created user context DTO.
     */
    public WDUserContextInfo createUserContext() {
        WDUserContextInfo result =
                WDWebSocketManager.sendAndWaitForResponse(new WDBrowserRequest.CreateUserContext(), WDUserContextInfo.class);
        System.out.println("User context created: " + result.getUserContext().value());
        return result;
    }

    /**
     * Retrieves the client windows of the browser.
     *
     * @return A list of client window IDs.
     * @throws RuntimeException if the operation fails.
     */
    public WDBrowserResult.GetClientWindowsResult getClientWindows() {
        WDBrowserResult.GetClientWindowsResult result =
                WDWebSocketManager.sendAndWaitForResponse(new WDBrowserRequest.GetClientWindows(), WDBrowserResult.GetClientWindowsResult.class);

        System.out.println("Client windows retrieved: " + result.getClientWindows());
        return result;
    }


    /**
     * Retrieves the user contexts available in the browser.
     *
     * @return A list of user context IDs.
     * @throws RuntimeException if the operation fails.
     */
    public WDBrowserResult.GetUserContextsResult getUserContexts() {
        WDBrowserResult.GetUserContextsResult result =
                WDWebSocketManager.sendAndWaitForResponse(new WDBrowserRequest.GetUserContexts(), WDBrowserResult.GetUserContextsResult.class);

        System.out.println("User contexts retrieved: " + result.getUserContexts());
        return result;
    }


    /**
     * Removes a user context from the browser.
     *
     * @param contextId The ID of the user context to remove.
     * @throws RuntimeException if the removal fails.
     */
    public void removeUserContext(String contextId) {
        WDWebSocketManager.sendAndWaitForResponse(new WDBrowserRequest.RemoveUserContext(contextId), WDEmptyResult.class);
        System.out.println("User context removed: " + contextId);
    }

    /**
     * Sets the state of a client window.
     *
     * @param clientWindowId The ID of the client window.
     * @param state          The state to set (e.g., "minimized", "maximized").
     * @throws RuntimeException if setting the state fails.
     */
    public WDClientWindowInfo setClientWindowState(String clientWindowId, String state) {
        WDClientWindowInfo result = WDWebSocketManager.sendAndWaitForResponse(new WDBrowserRequest.SetClientWindowState(clientWindowId, state), WDClientWindowInfo.class);
        System.out.println("Client window state set: " + result.getClientWindow().value());
        return result;
    }
}

