package com.aresstack.command.request.parameters.input;

import com.aresstack.type.browsingContext.WDBrowsingContext;
import com.aresstack.type.script.WDRemoteReference;
import com.aresstack.api.WDCommand;

import java.util.List;

public class SetFilesParameters implements WDCommand.Params {
    public final WDBrowsingContext WDBrowsingContext;
    public final WDRemoteReference.SharedReference sharedReference;
    List<String> files;

    public SetFilesParameters(WDBrowsingContext WDBrowsingContext, WDRemoteReference.SharedReference sharedReference, List<String> files) {
        this.WDBrowsingContext = WDBrowsingContext;
        this.sharedReference = sharedReference;
        this.files = files;
    }

    public WDBrowsingContext getBrowsingContext() {
        return WDBrowsingContext;
    }

    public WDRemoteReference.SharedReference getSharedReference() {
        return sharedReference;
    }

    public List<String> getFiles() {
        return files;
    }
}
