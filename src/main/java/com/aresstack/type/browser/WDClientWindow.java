package com.aresstack.type.browser;

import com.aresstack.api.markerInterfaces.WDType;
import com.aresstack.support.mapping.StringWrapper;

public class WDClientWindow implements WDType<WDClientWindow>, StringWrapper {
    private final String value;

    public WDClientWindow(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("ID must not be null or empty.");
        }
        this.value = value;
    }

    @Override // confirmed
    public String value() {
        return value;
    }
}