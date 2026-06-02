package com.aresstack.command.request.parameters.browsingContext;

import com.aresstack.type.browser.WDUserContext;
import com.aresstack.type.browsingContext.WDBrowsingContext;
import com.aresstack.api.WDCommand;

public class CreateParameters implements WDCommand.Params {
    private final CreateType type;
    private final WDBrowsingContext referenceContext; // optional
    private final Boolean background; // optional, default: false
    private final WDUserContext userContext; // optional

    public CreateParameters(CreateType type) {
        this(type, null, null, null);
    }

    public CreateParameters(CreateType type, WDBrowsingContext referenceContext, Boolean background, WDUserContext userContext) {
        this.type = type;
        this.referenceContext = referenceContext;
        this.background = background != null ? background : false;
        this.userContext = userContext;
    }

    public CreateType getType() {
        return type;
    }

    public WDBrowsingContext getReferenceContext() {
        return referenceContext;
    }

    public Boolean isBackground() {
        return background;
    }

    public WDUserContext getUserContext() {
        return userContext;
    }
}
