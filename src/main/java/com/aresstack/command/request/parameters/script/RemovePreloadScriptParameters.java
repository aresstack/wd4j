package com.aresstack.command.request.parameters.script;

import com.aresstack.type.script.WDPreloadScript;
import com.aresstack.api.WDCommand;

public class RemovePreloadScriptParameters implements WDCommand.Params {
    public final WDPreloadScript script;

    public RemovePreloadScriptParameters(WDPreloadScript script) {
        this.script = script;
    }

    public WDPreloadScript getScript() {
        return script;
    }
}
