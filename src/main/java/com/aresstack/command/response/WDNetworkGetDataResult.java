package com.aresstack.command.response;

import com.aresstack.api.markerInterfaces.WDResultData;
import com.aresstack.type.network.WDBytesValue;

/**
 * Result payload for network.getData.
 */
public class WDNetworkGetDataResult implements WDResultData {
    private final WDBytesValue bytes;

    public WDNetworkGetDataResult(WDBytesValue bytes) {
        this.bytes = bytes;
    }

    public WDBytesValue getBytes() {
        return bytes;
    }
}
