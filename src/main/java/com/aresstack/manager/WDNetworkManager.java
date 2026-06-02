package com.aresstack.manager;

import com.aresstack.command.request.parameters.network.GetDataParameters;
import com.aresstack.api.markerInterfaces.WDModule;
import com.aresstack.command.response.WDNetworkGetDataResult;
import com.aresstack.command.request.WDNetworkRequest;
import com.aresstack.command.request.parameters.network.AddInterceptParameters;
import com.aresstack.command.request.parameters.network.SetCacheBehaviorParameters;
import com.aresstack.command.response.WDEmptyResult;
import com.aresstack.command.response.WDNetworkResult;
import com.aresstack.type.browsingContext.WDBrowsingContext;
import com.aresstack.type.network.*;
import com.aresstack.api.WDWebSocketManager;

import java.util.List;

public class WDNetworkManager implements WDModule {

    private final WDWebSocketManager WDWebSocketManager;

    public WDNetworkManager(WDWebSocketManager WDWebSocketManager) {
        this.WDWebSocketManager = WDWebSocketManager;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Event Handlers
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Commands
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Adds an intercept for network requests matching the given URL pattern.
     *
     * @param phases The phases to intercept.
     * @return The intercept ID of the added rule.
     */
    public WDNetworkResult.AddInterceptResult addIntercept(List<AddInterceptParameters.InterceptPhase> phases) {
        return WDWebSocketManager.sendAndWaitForResponse(
                new WDNetworkRequest.AddIntercept(phases),
                WDNetworkResult.AddInterceptResult.class
        );
    }

    public  WDNetworkResult.AddInterceptResult addIntercept(List<AddInterceptParameters.InterceptPhase> phases, List<WDBrowsingContext> ctxs, List<WDUrlPattern> patterns) {
        return WDWebSocketManager.sendAndWaitForResponse(
                new WDNetworkRequest.AddIntercept(phases, ctxs, patterns),
                WDNetworkResult.AddInterceptResult.class
        );
    }

    /**
     * Continues a previously intercepted request.
     *
     * @param requestId The ID of the intercepted request.
     */
    public void continueRequest(String requestId) {
        WDWebSocketManager.sendAndWaitForResponse(new WDNetworkRequest.ContinueRequest(requestId), WDEmptyResult.class);
    }

    /**
     * Continues a previously intercepted response.
     *
     * @param requestId The ID of the intercepted response.
     */
    public void continueResponse(String requestId) {
        WDWebSocketManager.sendAndWaitForResponse(new WDNetworkRequest.ContinueResponse(requestId), WDEmptyResult.class);
    }

    /**
     * Continues a previously intercepted response without waiting for acknowledgement (fire-and-forget).
     * Used in intercept handlers where blocking would cause browser freezes due to request backlog.
     *
     * @param requestId The ID of the intercepted response.
     */
    public void continueResponseFireAndForget(String requestId) {
        WDWebSocketManager.sendFireAndForget(new WDNetworkRequest.ContinueResponse(requestId));
    }


    /**
     * Continues an authentication request with provided credentials or rejects it.
     *
     * @param requestId The ID of the intercepted authentication request.
     * @param authChallengeResponse The authentication challenge response.
     */
    public void continueWithAuth(String requestId, WDAuthCredentials authChallengeResponse) {
        WDWebSocketManager.sendAndWaitForResponse(new WDNetworkRequest.ContinueWithAuth(requestId, authChallengeResponse), WDEmptyResult.class);
    }

    /**
     * Fails an intercepted network request.
     *
     * @param requestId The ID of the intercepted request.
     */
    public void failRequest(String requestId) {
        WDWebSocketManager.sendAndWaitForResponse(new WDNetworkRequest.FailRequest(requestId), WDEmptyResult.class);
    }

    /**
     * Provides a custom response to an intercepted request.
     *
     * @param requestId The ID of the intercepted request.
     */
    public void provideResponse(String requestId) {
        WDWebSocketManager.sendAndWaitForResponse(new WDNetworkRequest.ProvideResponse(requestId), WDEmptyResult.class);
    }

    /**
     * Removes a previously added network request interception rule.
     *
     * @param interceptId The ID of the intercept to remove.
     */
    public void removeIntercept(String interceptId) {
        WDWebSocketManager.sendAndWaitForResponse(new WDNetworkRequest.RemoveIntercept(interceptId), WDEmptyResult.class);
    }

    /**
     * Sets the cache behavior for the given context.
     *
     * @param cacheBehavior The cache behavior to set.
     */
    public void setCacheBehavior(SetCacheBehaviorParameters.CacheBehavior cacheBehavior) {
        WDWebSocketManager.sendAndWaitForResponse(new WDNetworkRequest.SetCacheBehavior(cacheBehavior), WDEmptyResult.class);
    }

    // === UPDATED TO NEW SPECS ===

    /** Add a data collector. */
    public com.aresstack.type.network.WDCollector addDataCollector(List<WDDataType> dataTypes, int maxEncodedDataSize) {
        WDNetworkResult.AddDataCollectorResult result =
                WDWebSocketManager.sendAndWaitForResponse(
                        new WDNetworkRequest.AddDataCollector(dataTypes, maxEncodedDataSize),
                        WDNetworkResult.AddDataCollectorResult.class
                );
        return result.getCollector();
    }

    /** Convenience: Add a response body collector for all contexts. */
    public com.aresstack.type.network.WDCollector addResponseBodyCollector(int maxEncodedDataSize) {
        List<WDDataType> types = java.util.Collections.singletonList(WDDataType.RESPONSE);
        return addDataCollector(types, maxEncodedDataSize);
    }

    /** Get collected data (bytes) for a request. Set disown=true to release association. */
    public WDBytesValue getData(WDDataType dataType,
                                WDRequest request,
                                com.aresstack.type.network.WDCollector collector,
                                Boolean disown) {
        WDNetworkGetDataResult result =
                WDWebSocketManager.sendAndWaitForResponse(
                        new WDNetworkRequest.GetData(
                                new GetDataParameters(dataType, collector, disown, request)
                        ),
                        WDNetworkGetDataResult.class
                );
        return result.getBytes();
    }

    /** Disown previously collected data for a given request and collector. */
    public void disownData(WDDataType dataType,
                           com.aresstack.type.network.WDCollector collector,
                           WDRequest request) {
        WDWebSocketManager.sendAndWaitForResponse(
                new WDNetworkRequest.DisownData(dataType, collector, request),
                WDEmptyResult.class
        );
    }

    /** Remove a data collector. */
    public void removeDataCollector(com.aresstack.type.network.WDCollector collector) {
        WDWebSocketManager.sendAndWaitForResponse(
                new WDNetworkRequest.RemoveDataCollector(collector),
                WDEmptyResult.class
        );
    }

    /** Set extra headers for matching contexts/userContexts. */
    public void setExtraHeaders(List<WDHeader> headers) {
        WDWebSocketManager.sendAndWaitForResponse(
                new WDNetworkRequest.SetExtraHeaders(headers),
                WDEmptyResult.class
        );
    }
}