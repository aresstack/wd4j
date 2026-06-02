package com.aresstack.command.request.parameters.emulation;

import com.aresstack.api.WDCommand;
import com.aresstack.type.browsingContext.WDBrowsingContext;
import com.aresstack.type.browser.WDUserContext;
import java.util.List;

/**
 * Parameters for emulation.setLocaleOverride.
 */
public class SetLocaleOverrideParameters implements WDCommand.Params {
    private String locale; // nullable
    private List<WDBrowsingContext> contexts; // optional
    private List<WDUserContext> userContexts; // optional

    public SetLocaleOverrideParameters(String locale) {
        this.locale = locale;
    }

    public SetLocaleOverrideParameters(String locale, List<WDBrowsingContext> contexts) {
        this.locale = locale;
        this.contexts = contexts;
    }

    public SetLocaleOverrideParameters(List<WDUserContext> userContexts, String locale) {
        this.locale = locale;
        this.userContexts = userContexts;
    }

    public String getLocale() { return locale; }
    public List<WDBrowsingContext> getContexts() { return contexts; }
    public List<WDUserContext> getUserContexts() { return userContexts; }
}