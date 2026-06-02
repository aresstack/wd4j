package com.aresstack.command.request;

import com.aresstack.api.markerInterfaces.WDCommandData;
import com.aresstack.command.request.helper.WDCommandImpl;
import com.aresstack.command.request.parameters.input.PerformActionsParameters;
import com.aresstack.command.request.parameters.input.ReleaseActionsParameters;
import com.aresstack.command.request.parameters.input.SetFilesParameters;
import com.aresstack.command.request.parameters.input.sourceActions.SourceActions;
import com.aresstack.type.browsingContext.WDBrowsingContext;
import com.aresstack.type.script.WDRemoteReference;

import java.util.List;

public class WDInputRequest {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Commands (Classes)
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class PerformActions extends WDCommandImpl<PerformActionsParameters> implements WDCommandData {
        public PerformActions(String contextId, List<SourceActions> actions) {
            super("input.performActions", new PerformActionsParameters(new WDBrowsingContext(contextId), actions));
        }
        public PerformActions(WDBrowsingContext context, List<SourceActions> actions) {
            super("input.performActions", new PerformActionsParameters(context, actions));
        }
    }

    public static class ReleaseActions extends WDCommandImpl<ReleaseActionsParameters> implements WDCommandData {
        public ReleaseActions(String contextId) {
            super("input.releaseActions", new ReleaseActionsParameters(new WDBrowsingContext(contextId)));
        }
        public ReleaseActions(WDBrowsingContext context) {
            super("input.releaseActions", new ReleaseActionsParameters(context));
        }
    }

    public static class SetFiles extends WDCommandImpl<SetFilesParameters> implements WDCommandData {
        public SetFiles(String contextId, WDRemoteReference.SharedReference sharedReference, List<String> files) {
            super("input.setFiles", new SetFilesParameters(new WDBrowsingContext(contextId), sharedReference, files));
        }
        public SetFiles(WDBrowsingContext context, WDRemoteReference.SharedReference sharedReference, List<String> files) {
            super("input.setFiles", new SetFilesParameters(context, sharedReference, files));
        }
    }

}