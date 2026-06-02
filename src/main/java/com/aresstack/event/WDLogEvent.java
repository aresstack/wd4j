package com.aresstack.event;

import com.google.gson.JsonObject;
import com.aresstack.api.markerInterfaces.WDModule;
import com.aresstack.websocket.WDEvent;
import com.aresstack.type.log.WDLogEntry;
import com.aresstack.websocket.WDEventNames;

public class WDLogEvent implements WDModule {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Event Types (Classes)
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class EntryAdded extends WDEvent<WDLogEntry /*aka. EntryAdded*/> {
        private final String method = WDEventNames.ENTRY_ADDED.getName();

        public EntryAdded(JsonObject json) {
            super(json, WDLogEntry.class);
        }

        @Override
        public String getMethod() {
            return method;
        }
    }
}
