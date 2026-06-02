package com.aresstack.type.input;

import com.aresstack.type.browsingContext.WDBrowsingContext;
import com.aresstack.type.script.WDRemoteReference;

/**
 * Represent the input.FileDialogInfo struct.
 */
public class WDFileDialogInfo {
    private WDBrowsingContext context;
    private WDRemoteReference.SharedReference element; // optional
    private boolean multiple;

    public WDFileDialogInfo() {
        // Required for Gson no-args constructor
    }

    public WDFileDialogInfo(WDBrowsingContext context, WDRemoteReference.SharedReference element, boolean multiple) {
        this.context = context;
        this.element = element;
        this.multiple = multiple;
    }

    public WDBrowsingContext getContext() { return context; }
    public WDRemoteReference.SharedReference getElement() { return element; }
    public boolean isMultiple() { return multiple; }
}