package com.aresstack.command.response;

import com.aresstack.api.markerInterfaces.WDResultData;
import com.aresstack.type.webExtension.WDExtension;

public interface WDWebExtensionResult extends WDResultData {
    class InstallResult implements WDWebExtensionResult {
        private final WDExtension extension;

        public InstallResult(WDExtension extension) {
            this.extension = extension;
        }

        public WDExtension getExtension() {
            return extension;
        }
    }
}
