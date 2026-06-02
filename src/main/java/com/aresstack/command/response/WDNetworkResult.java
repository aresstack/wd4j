package com.aresstack.command.response;

import com.aresstack.api.markerInterfaces.WDResultData;
import com.aresstack.type.network.WDIntercept;

public interface WDNetworkResult extends WDResultData {
    class AddInterceptResult implements WDNetworkResult {
        WDIntercept intercept;

        public AddInterceptResult(WDIntercept intercept) {
            this.intercept = intercept;
        }

        public WDIntercept getIntercept() {
            return intercept;
        }
    }

    class AddDataCollectorResult implements WDNetworkResult {
        com.aresstack.type.network.WDCollector collector;

        public AddDataCollectorResult(com.aresstack.type.network.WDCollector collector) {
            this.collector = collector;
        }

        public com.aresstack.type.network.WDCollector getCollector() {
            return collector;
        }
    }
}
