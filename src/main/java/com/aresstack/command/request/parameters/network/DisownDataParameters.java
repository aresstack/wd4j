package com.aresstack.command.request.parameters.network;

import com.aresstack.api.WDCommand;
import com.aresstack.type.network.WDCollector;
import com.aresstack.type.network.WDDataType;
import com.aresstack.type.network.WDRequest;

/**
 * Parameters for network.disownData
 * Spec: https://www.w3.org/TR/webdriver-bidi/#command-network-disownData
 */
public class DisownDataParameters implements WDCommand.Params {
    private final WDDataType dataType;
    private final WDCollector collector;
    private final WDRequest request;

    public DisownDataParameters(WDDataType dataType, WDCollector collector, WDRequest request) {
        if (dataType == null) {
            throw new IllegalArgumentException("dataType must not be null.");
        }
        if (collector == null) {
            throw new IllegalArgumentException("collector must not be null.");
        }
        if (request == null) {
            throw new IllegalArgumentException("request must not be null.");
        }
        this.dataType = dataType;
        this.collector = collector;
        this.request = request;
    }

    public WDDataType getDataType() {
        return dataType;
    }

    public WDCollector getCollector() {
        return collector;
    }

    public WDRequest getRequest() {
        return request;
    }
}
