package com.aresstack.type.network;

import com.aresstack.support.mapping.StringWrapper;

public class WDIntercept implements StringWrapper {
    private final String value;

    public WDIntercept(String value) {
        this.value = value;
    }

    @Override // confirmed
    public String value() {
        return value;
    }
}