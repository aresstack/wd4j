package com.aresstack.command.request.parameters.network;

import com.aresstack.type.network.WDCookie;

import java.util.List;

public class CookieHeader {
    private final List<WDCookie> cookies;

    public CookieHeader(List<WDCookie> cookies) {
        this.cookies = cookies;
    }

    public List<WDCookie> getCookies() {
        return cookies;
    }
}
