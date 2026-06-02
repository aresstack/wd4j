package com.aresstack.command.request.parameters.input;

import com.aresstack.command.request.parameters.input.sourceActions.SourceActions;
import com.aresstack.type.browsingContext.WDBrowsingContext;
import com.aresstack.api.WDCommand;

import java.util.List;

public class PerformActionsParameters implements WDCommand.Params {
    private final WDBrowsingContext context;
    private final List<SourceActions> actions;

    public PerformActionsParameters(WDBrowsingContext context, List<SourceActions> actions) {
        this.context = context;
        this.actions = actions;
    }

    public WDBrowsingContext getBrowsingContext() {
        return context;
    }

}
