package com.aresstack.command.request.parameters.script;

import com.aresstack.type.browsingContext.WDBrowsingContext;
import com.aresstack.type.script.WDRealmType;
import com.aresstack.api.WDCommand;

public class GetRealmsParameters implements WDCommand.Params {
    public final WDBrowsingContext WDBrowsingContextRequest; // Optional
    public final WDRealmType type; // Optional

    public GetRealmsParameters() {
        this(null, null);
    }

    public GetRealmsParameters(WDBrowsingContext WDBrowsingContext, WDRealmType type) {
        this.WDBrowsingContextRequest = WDBrowsingContext;
        this.type = type;
    }

    public WDBrowsingContext getBrowsingContext() {
        return WDBrowsingContextRequest;
    }

    public WDRealmType getType() {
        return type;
    }
}
