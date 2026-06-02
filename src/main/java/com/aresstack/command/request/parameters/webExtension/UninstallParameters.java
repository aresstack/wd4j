package com.aresstack.command.request.parameters.webExtension;

import com.aresstack.type.webExtension.WDExtension;
import com.aresstack.api.WDCommand;

public class UninstallParameters implements WDCommand.Params {
    private final WDExtension WDExtension;

    public UninstallParameters(WDExtension WDExtension) {
        this.WDExtension = WDExtension;
    }

    public WDExtension getExtension() {
        return WDExtension;
    }
}
