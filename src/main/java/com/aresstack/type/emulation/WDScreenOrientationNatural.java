package com.aresstack.type.emulation;

import com.aresstack.support.mapping.EnumWrapper;

/**
 * Represent the natural orientation: "portrait" | "landscape".
 */
public enum WDScreenOrientationNatural implements EnumWrapper {
    PORTRAIT("portrait"),
    LANDSCAPE("landscape");

    private final String value;

    WDScreenOrientationNatural(String value) { this.value = value; }

    @Override
    public String value() { return value; }
}