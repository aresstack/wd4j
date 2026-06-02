package com.aresstack.command.request.parameters.emulation;

import com.aresstack.api.WDCommand;
import com.aresstack.type.browsingContext.WDBrowsingContext;
import com.aresstack.type.browser.WDUserContext;
import com.aresstack.type.emulation.WDScreenOrientation;
import java.util.List;

/**
 * Parameters for emulation.setScreenOrientationOverride.
 */
public class SetScreenOrientationOverrideParameters implements WDCommand.Params {
    private WDScreenOrientation screenOrientation; // nullable
    private List<WDBrowsingContext> contexts; // optional
    private List<WDUserContext> userContexts; // optional

    public SetScreenOrientationOverrideParameters(WDScreenOrientation screenOrientation) {
        this.screenOrientation = screenOrientation;
    }

    public SetScreenOrientationOverrideParameters(WDScreenOrientation screenOrientation, List<WDBrowsingContext> contexts) {
        this.screenOrientation = screenOrientation;
        this.contexts = contexts;
    }

    public SetScreenOrientationOverrideParameters(List<WDUserContext> userContexts, WDScreenOrientation screenOrientation) {
        this.screenOrientation = screenOrientation;
        this.userContexts = userContexts;
    }

    public WDScreenOrientation getScreenOrientation() { return screenOrientation; }
    public List<WDBrowsingContext> getContexts() { return contexts; }
    public List<WDUserContext> getUserContexts() { return userContexts; }
}