package com.aresstack.command.request.parameters.browsingContext;

import com.aresstack.type.browsingContext.WDBrowsingContext;
import com.aresstack.type.browsingContext.WDLocator;
import com.aresstack.type.script.WDRemoteReference;
import com.aresstack.type.script.WDSerializationOptions;
import com.aresstack.api.WDCommand;

import java.util.List;

public class LocateNodesParameters implements WDCommand.Params {
    private final WDBrowsingContext context;
    private final WDLocator locator;
    private final Integer maxNodeCount; // optional
    private final WDSerializationOptions serializationOptions; // optional
    private final List<WDRemoteReference.SharedReference> startNodes; // optional

    public LocateNodesParameters(WDBrowsingContext context, WDLocator locator) {
        this(context, locator, null, null, null);
    }

    public LocateNodesParameters(WDBrowsingContext context, WDLocator locator, Integer maxNodeCount, WDSerializationOptions serializationOptions, List<WDRemoteReference.SharedReference> startNodes) {
        this.context = context;
        this.locator = locator;
        this.maxNodeCount = maxNodeCount;
        this.serializationOptions = serializationOptions;
        this.startNodes = startNodes;
    }

    public WDBrowsingContext getContext() {
        return context;
    }

    public WDLocator getLocator() {
        return locator;
    }

    public Integer getMaxNodeCount() {
        return maxNodeCount;
    }

    public WDSerializationOptions getSerializationOptions() {
        return serializationOptions;
    }

    public List<WDRemoteReference.SharedReference> getStartNodes() {
        return startNodes;
    }
}
