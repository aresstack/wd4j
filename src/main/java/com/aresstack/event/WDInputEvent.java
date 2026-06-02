package com.aresstack.event;

import com.google.gson.JsonObject;
import com.aresstack.api.markerInterfaces.WDModule;
import com.aresstack.type.input.WDFileDialogInfo;
import com.aresstack.websocket.WDEvent;
import com.aresstack.websocket.WDEventNames;

/**
 * Input module events.
 * Additional input events (e.g. file dialogs) are expressed as nested static classes.
 */
public class WDInputEvent implements WDModule {
    public WDInputEvent(JsonObject json) {
        // Intentionally empty – the presence of this constructor preserves the API shape.
    }

    /** Event fired when a file chooser dialog opens. */
    public static class FileDialogOpened extends WDEvent<WDFileDialogInfo> {
        private final String method = WDEventNames.FILE_DIALOG_OPENED.getName();

        public FileDialogOpened(JsonObject json) {
            // NOTE: Json passed here must be the event 'params' object.
            super(json, WDFileDialogInfo.class);
        }

        @Override
        public String getMethod() {
            return method;
        }
    }
}