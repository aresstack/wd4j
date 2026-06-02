package com.aresstack.command.request.parameters.browsingContext;

import com.aresstack.type.browsingContext.WDBrowsingContext;
import com.aresstack.api.WDCommand;

/**
 * The browsingContext.traverseHistory command traverses the history of a given navigable by a delta.
 */
public class TraverseHistoryParameters implements WDCommand.Params {
    private final WDBrowsingContext context;
    private final long delta;

    public TraverseHistoryParameters(WDBrowsingContext browsingContext, long delta) {
        this.context = browsingContext;
        this.delta = delta;
    }

    public WDBrowsingContext getBrowsingContext() {
        return context;
    }

    public long getDelta() {
        return delta;
    }
}
