package com.aresstack.command.request.parameters.emulation;

import com.aresstack.api.WDCommand;
import com.aresstack.type.browsingContext.WDBrowsingContext;
import com.aresstack.type.browser.WDUserContext;
import com.aresstack.type.emulation.WDForcedColorsModeTheme;

import java.util.List;

/**
 * Parameters for emulation.setForcedColorsModeThemeOverride.
 * At least one of contexts or userContexts may be provided; both cannot be used simultaneously.
 */
public class SetForcedColorsModeThemeOverrideParameters implements WDCommand.Params {
    private WDForcedColorsModeTheme theme; // nullable
    private List<WDBrowsingContext> contexts; // optional
    private List<WDUserContext> userContexts; // optional

    public SetForcedColorsModeThemeOverrideParameters(WDForcedColorsModeTheme theme) {
        this.theme = theme;
    }

    public SetForcedColorsModeThemeOverrideParameters(WDForcedColorsModeTheme theme, List<WDBrowsingContext> contexts) {
        this.theme = theme;
        this.contexts = contexts;
    }

    public SetForcedColorsModeThemeOverrideParameters(List<WDUserContext> userContexts, WDForcedColorsModeTheme theme) {
        this.theme = theme;
        this.userContexts = userContexts;
    }

    public WDForcedColorsModeTheme getTheme() { return theme; }
    public List<WDBrowsingContext> getContexts() { return contexts; }
    public List<WDUserContext> getUserContexts() { return userContexts; }
}