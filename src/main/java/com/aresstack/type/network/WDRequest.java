package com.aresstack.type.network;

import com.aresstack.support.mapping.StringWrapper;

public class WDRequest implements StringWrapper {
    private final String value;

    public WDRequest(String id) {
        this.value = id;
    }

    @Override // confirmed
    public String value() {
        return value;
    }

}