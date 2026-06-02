package com.aresstack.command.request.parameters.network;

import com.aresstack.api.WDCommand;
import com.aresstack.type.browsingContext.WDBrowsingContext;
import com.aresstack.type.browser.WDUserContext;
import com.aresstack.type.network.WDCollectorType;
import com.aresstack.type.network.WDDataType;

import java.util.List;

/**
 * Parameters for network.addDataCollector
 * Spec: https://www.w3.org/TR/webdriver-bidi/#command-network-addDataCollector
 */
public class AddDataCollectorParameters implements WDCommand.Params {
    private final List<WDDataType> dataTypes;
    private final Integer maxEncodedDataSize;
    private final WDCollectorType collectorType; // Optional, defaults to "blob"
    private final List<WDBrowsingContext> contexts; // Optional
    private final List<WDUserContext> userContexts; // Optional

    public AddDataCollectorParameters(List<WDDataType> dataTypes, Integer maxEncodedDataSize) {
        this(dataTypes, maxEncodedDataSize, null, null, null);
    }

    public AddDataCollectorParameters(List<WDDataType> dataTypes, Integer maxEncodedDataSize,
                                      WDCollectorType collectorType,
                                      List<WDBrowsingContext> contexts,
                                      List<WDUserContext> userContexts) {
        if (dataTypes == null || dataTypes.isEmpty()) {
            throw new IllegalArgumentException("dataTypes must not be null or empty.");
        }
        if (maxEncodedDataSize == null || maxEncodedDataSize < 0) {
            throw new IllegalArgumentException("maxEncodedDataSize must be >= 0.");
        }
        this.dataTypes = dataTypes;
        this.maxEncodedDataSize = maxEncodedDataSize;
        this.collectorType = collectorType;
        this.contexts = contexts;
        this.userContexts = userContexts;
    }

    public List<WDDataType> getDataTypes() {
        return dataTypes;
    }

    public Integer getMaxEncodedDataSize() {
        return maxEncodedDataSize;
    }

    public WDCollectorType getCollectorType() {
        return collectorType;
    }

    public List<WDBrowsingContext> getContexts() {
        return contexts;
    }

    public List<WDUserContext> getUserContexts() {
        return userContexts;
    }
}
