package com.aresstack.command.request.parameters.network;

import com.aresstack.type.network.WDAuthCredentials;
import com.aresstack.type.network.WDHeader;
import com.aresstack.type.network.WDRequest;
import com.aresstack.type.network.WDSetCookieHeader;
import com.aresstack.api.WDCommand;

import java.util.List;

public class ContinueResponseParameters implements WDCommand.Params {
    private final WDRequest request;
    private final List<WDSetCookieHeader> cookies; // Optional
    private final WDAuthCredentials rawResponse; // Optional
    private final List<WDHeader> responseWDHeaders; // Optional
    private final String text; // Optional
    private final Integer statusCode; // Optional

    public ContinueResponseParameters(WDRequest request) {
        this(request, null, null, null, null, null);
    }

    public ContinueResponseParameters(WDRequest request, List<WDSetCookieHeader> cookies, WDAuthCredentials rawResponse, List<WDHeader> responseWDHeaders, String text, Integer statusCode) {
        this.request = request;
        this.cookies = cookies;
        this.rawResponse = rawResponse;
        this.responseWDHeaders = responseWDHeaders;
        this.text = text;
        this.statusCode = statusCode;
    }

    public WDRequest getRequest() {
        return request;
    }

    public List<WDSetCookieHeader> getCookies() {
        return cookies;
    }

    public WDAuthCredentials getRawResponse() {
        return rawResponse;
    }

    public List<WDHeader> getResponseHeaders() {
        return responseWDHeaders;
    }

    public String getText() {
        return text;
    }
}
