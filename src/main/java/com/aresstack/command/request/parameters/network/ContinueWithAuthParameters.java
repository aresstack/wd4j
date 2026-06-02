package com.aresstack.command.request.parameters.network;

import com.aresstack.support.mapping.EnumWrapper;
import com.aresstack.type.network.WDRequest;
import com.aresstack.api.WDCommand;

public abstract class ContinueWithAuthParameters implements WDCommand.Params {
    private final WDRequest request;

    public ContinueWithAuthParameters(WDRequest request) {
        this.request = request;
    }

    public WDRequest getRequest() {
        return request;
    }

    public interface Action extends EnumWrapper {

    }
}
