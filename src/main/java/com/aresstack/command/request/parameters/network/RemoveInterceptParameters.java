package com.aresstack.command.request.parameters.network;

import com.aresstack.type.network.WDIntercept;
import com.aresstack.api.WDCommand;

public class RemoveInterceptParameters implements WDCommand.Params {
    private final WDIntercept intercept;

    public RemoveInterceptParameters(WDIntercept intercept) {
        this.intercept = intercept;
    }

    public WDIntercept getIntercept() {
        return intercept;
    }
}
