package com.aresstack.command.request.parameters.network;

import com.aresstack.api.WDCommand;
import com.aresstack.type.network.WDCollector;

/**
 * Parameters for network.removeDataCollector
 * Spec: https://www.w3.org/TR/webdriver-bidi/#command-network-removeDataCollector
 */
public class RemoveDataCollectorParameters implements WDCommand.Params {
    private final WDCollector collector;

    public RemoveDataCollectorParameters(WDCollector collector) {
        if (collector == null) {
            throw new IllegalArgumentException("collector must not be null.");
        }
        this.collector = collector;
    }

    public WDCollector getCollector() {
        return collector;
    }
}
