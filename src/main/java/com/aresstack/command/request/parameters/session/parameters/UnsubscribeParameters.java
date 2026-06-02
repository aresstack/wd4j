package com.aresstack.command.request.parameters.session.parameters;

import com.aresstack.type.browsingContext.WDBrowsingContext;
import com.aresstack.type.session.WDSubscription;
import com.aresstack.type.session.WDUnsubscribeByAttributesRequest;
import com.aresstack.type.session.WDUnsubscribeByIDRequest;
import com.aresstack.api.WDCommand;

import java.util.List;

public interface UnsubscribeParameters extends WDCommand.Params {

    public class WDUnsubscribeByAttributesRequestParams extends WDUnsubscribeByAttributesRequest implements UnsubscribeParameters {
        public WDUnsubscribeByAttributesRequestParams(List<String> events) {
            super(events, null);
        }

        public WDUnsubscribeByAttributesRequestParams(List<String> events, List<WDBrowsingContext> contexts) {
            super(events, contexts);
        }
    }

    // ToDo: Not supported in Firefox yet?
    public class WDUnsubscribeByIDRequestParams extends WDUnsubscribeByIDRequest implements UnsubscribeParameters {
        public WDUnsubscribeByIDRequestParams(List<WDSubscription> WDSubscriptions) {
            super(WDSubscriptions);
        }
    }
}
