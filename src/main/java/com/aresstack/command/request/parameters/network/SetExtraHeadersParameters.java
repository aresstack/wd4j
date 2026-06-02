package com.aresstack.command.request.parameters.network;

import com.aresstack.api.WDCommand;
import com.aresstack.type.browsingContext.WDBrowsingContext;
import com.aresstack.type.browser.WDUserContext;
import com.aresstack.type.network.WDHeader;

import java.util.List;

/**
 * Parameters for network.setExtraHeaders
 * Spec: https://www.w3.org/TR/webdriver-bidi/#command-network-setExtraHeaders
 */
public class SetExtraHeadersParameters implements WDCommand.Params {
    private final List<WDHeader> headers;
    private final List<WDBrowsingContext> contexts; // Optional
    private final List<WDUserContext> userContexts; // Optional

    public SetExtraHeadersParameters(List<WDHeader> headers) {
        this(headers, null, null);
    }

    public SetExtraHeadersParameters(List<WDHeader> headers,
                                     List<WDBrowsingContext> contexts,
                                     List<WDUserContext> userContexts) {
        if (headers == null || headers.isEmpty()) {
            throw new IllegalArgumentException("headers must not be null or empty.");
        }
        this.headers = headers;
        this.contexts = contexts;
        this.userContexts = userContexts;
    }

    public List<WDHeader> getHeaders() {
        return headers;
    }

    public List<WDBrowsingContext> getContexts() {
        return contexts;
    }

    public List<WDUserContext> getUserContexts() {
        return userContexts;
    }
}
