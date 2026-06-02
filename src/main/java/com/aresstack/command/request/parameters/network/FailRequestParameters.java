package com.aresstack.command.request.parameters.network;

import com.aresstack.type.network.WDRequest;
import com.aresstack.api.WDCommand;

public class FailRequestParameters implements WDCommand.Params {
    private final WDRequest request;

    public FailRequestParameters(WDRequest request) {
        this.request = request;
    }

    public WDRequest getRequest() {
        return request;
    }
}
