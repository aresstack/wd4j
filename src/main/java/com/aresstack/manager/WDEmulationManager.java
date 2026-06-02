package com.aresstack.manager;

import com.aresstack.api.WDCommand;
import com.aresstack.api.WDWebSocketManager;
import com.aresstack.command.request.WDEmulationRequest;
import com.aresstack.command.request.parameters.emulation.*;
import com.aresstack.type.emulation.WDForcedColorsModeTheme;
import com.aresstack.type.emulation.WDGeolocationCoordinates;
import com.aresstack.type.emulation.WDGeolocationPositionError;
import com.aresstack.type.emulation.WDScreenOrientation;
import com.aresstack.command.request.parameters.emulation.*;
import com.aresstack.command.response.WDEmptyResult;
import com.aresstack.type.browsingContext.WDBrowsingContext;
import com.aresstack.type.browser.WDUserContext;
import com.aresstack.type.emulation.*;

import java.util.List;

/**
 * Facade for emulation.* commands.
 * Provides higher-level methods that map directly to WebDriver BiDi emulation commands.
 * Each method hides details of request construction and response handling.
 */
public class WDEmulationManager {
    private final WDWebSocketManager ws;

    public WDEmulationManager(WDWebSocketManager ws) {
        this.ws = ws;
    }

    // ── Forced Colors ─────────────────────────────────────────────────────────────
    public void setForcedColorsModeThemeOverride(WDForcedColorsModeTheme theme, List<WDBrowsingContext> contexts) {
        send(new WDEmulationRequest.SetForcedColorsModeThemeOverride(new SetForcedColorsModeThemeOverrideParameters(theme, contexts)));
    }
    public void setForcedColorsModeThemeOverrideUser(List<WDUserContext> userContexts, WDForcedColorsModeTheme theme) {
        send(new WDEmulationRequest.SetForcedColorsModeThemeOverride(new SetForcedColorsModeThemeOverrideParameters(userContexts, theme)));
    }

    // ── Geolocation ───────────────────────────────────────────────────────────────
    public void setGeolocationOverride(WDGeolocationCoordinates coords, List<WDBrowsingContext> contexts) {
        send(new WDEmulationRequest.SetGeolocationOverride(new SetGeolocationOverrideParameters(coords, contexts)));
    }
    public void setGeolocationOverrideError(WDGeolocationPositionError error, List<WDUserContext> userContexts) {
        send(new WDEmulationRequest.SetGeolocationOverride(new SetGeolocationOverrideParameters(error, userContexts)));
    }

    // ── Locale ───────────────────────────────────────────────────────────────────
    public void setLocaleOverride(String locale, List<WDBrowsingContext> contexts) {
        send(new WDEmulationRequest.SetLocaleOverride(new SetLocaleOverrideParameters(locale, contexts)));
    }
    public void setLocaleOverrideUser(List<WDUserContext> userContexts, String locale) {
        send(new WDEmulationRequest.SetLocaleOverride(new SetLocaleOverrideParameters(userContexts, locale)));
    }

    // ── Screen Orientation ───────────────────────────────────────────────────────
    public void setScreenOrientationOverride(WDScreenOrientation orientation, List<WDBrowsingContext> contexts) {
        send(new WDEmulationRequest.SetScreenOrientationOverride(new SetScreenOrientationOverrideParameters(orientation, contexts)));
    }
    public void setScreenOrientationOverrideUser(List<WDUserContext> userContexts, WDScreenOrientation orientation) {
        send(new WDEmulationRequest.SetScreenOrientationOverride(new SetScreenOrientationOverrideParameters(userContexts, orientation)));
    }

    // ── Scripting Enabled ────────────────────────────────────────────────────────
    public void setScriptingEnabled(Boolean enabled, List<WDBrowsingContext> contexts) {
        send(new WDEmulationRequest.SetScriptingEnabled(new SetScriptingEnabledParameters(enabled, contexts)));
    }
    public void setScriptingEnabledUser(List<WDUserContext> userContexts, Boolean enabled) {
        send(new WDEmulationRequest.SetScriptingEnabled(new SetScriptingEnabledParameters(userContexts, enabled)));
    }

    // ── Timezone ─────────────────────────────────────────────────────────────────
    public void setTimezoneOverride(String timezone, List<WDBrowsingContext> contexts) {
        send(new WDEmulationRequest.SetTimezoneOverride(new SetTimezoneOverrideParameters(timezone, contexts)));
    }
    public void setTimezoneOverrideUser(List<WDUserContext> userContexts, String timezone) {
        send(new WDEmulationRequest.SetTimezoneOverride(new SetTimezoneOverrideParameters(userContexts, timezone)));
    }

    // ── Internal helper ──────────────────────────────────────────────────────────
    private void send(Object cmd) {
        ws.sendAndWaitForResponse((WDCommand) cmd, WDEmptyResult.class);
    }
}