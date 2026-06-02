package com.aresstack.command.request.parameters.emulation;

import com.aresstack.api.WDCommand;
import com.aresstack.type.browsingContext.WDBrowsingContext;
import com.aresstack.type.browser.WDUserContext;
import com.aresstack.type.emulation.WDGeolocationCoordinates;
import com.aresstack.type.emulation.WDGeolocationPositionError;

import java.util.List;

/**
 * Parameters for emulation.setGeolocationOverride.
 * Exactly one of coordinates or error must be provided (spec constraint).
 */
public class SetGeolocationOverrideParameters implements WDCommand.Params {
    private WDGeolocationCoordinates coordinates; // nullable
    private WDGeolocationPositionError error;     // nullable (mutually exclusive with coordinates)
    private List<WDBrowsingContext> contexts;     // optional
    private List<WDUserContext> userContexts;     // optional

    public SetGeolocationOverrideParameters(WDGeolocationCoordinates coordinates) {
        this.coordinates = coordinates;
    }

    public SetGeolocationOverrideParameters(WDGeolocationPositionError error) {
        this.error = error;
    }

    public SetGeolocationOverrideParameters(WDGeolocationCoordinates coordinates, List<WDBrowsingContext> contexts) {
        this.coordinates = coordinates;
        this.contexts = contexts;
    }

    public SetGeolocationOverrideParameters(WDGeolocationPositionError error, List<WDUserContext> userContexts) {
        this.error = error;
        this.userContexts = userContexts;
    }

    public WDGeolocationCoordinates getCoordinates() { return coordinates; }
    public WDGeolocationPositionError getError() { return error; }
    public List<WDBrowsingContext> getContexts() { return contexts; }
    public List<WDUserContext> getUserContexts() { return userContexts; }
}