package com.aresstack.command.request.parameters.browser;

import com.aresstack.type.browser.WDUserContext;
import com.aresstack.api.WDCommand;

public class RemoveUserContextParameters implements WDCommand.Params {
    private final WDUserContext userContext;

    public RemoveUserContextParameters(WDUserContext userContext) {
        this.userContext = userContext;
    }

    public WDUserContext getUserContext() {
        return userContext;
    }
}
