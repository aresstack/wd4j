package com.aresstack.command.request.parameters.browsingContext;

import com.aresstack.type.browsingContext.WDBrowsingContext;
import com.aresstack.api.WDCommand;

public class ActivateParameters implements WDCommand.Params {
    private final WDBrowsingContext context;

    public ActivateParameters(WDBrowsingContext context) {
        this.context = context;
    }

    public WDBrowsingContext getContext() {
        return context;
    }
}
