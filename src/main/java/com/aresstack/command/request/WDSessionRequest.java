package com.aresstack.command.request;

import com.aresstack.api.markerInterfaces.WDCommandData;
import com.aresstack.command.request.helper.WDCommandImpl;
import com.aresstack.command.request.helper.WDEmptyParameters;
import com.aresstack.command.request.parameters.session.parameters.NewParameters;
import com.aresstack.command.request.parameters.session.parameters.UnsubscribeParameters;
import com.aresstack.type.session.WDCapabilitiesRequest;
import com.aresstack.type.session.WDCapabilityRequest;
import com.aresstack.type.session.WDSubscriptionRequest;

import java.util.List;

public class WDSessionRequest {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Commands (Classes)
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class Status extends WDCommandImpl<WDEmptyParameters> implements WDCommandData {
        public Status() {
            super("session.status", new WDEmptyParameters());
        }
    }


    public static class New extends WDCommandImpl<NewParameters> implements WDCommandData {
        public New(String browserName) {
            super("session.new", new NewParameters(new WDCapabilitiesRequest(new WDCapabilityRequest(
                    null, browserName, null,
                    null, null, null))));
        }
        public New(WDCapabilitiesRequest capabilities) {
            super("session.new", new NewParameters(capabilities));
        }
    }

    public static class End extends WDCommandImpl<WDEmptyParameters> implements WDCommandData {
        public End() {
            super("session.end", new WDEmptyParameters());
        }
    }

    public static class Subscribe extends WDCommandImpl<WDSubscriptionRequest> implements WDCommandData {
        @Deprecated
        public Subscribe(List<String> events) {
            super("session.subscribe", new WDSubscriptionRequest(events));
        }
        public Subscribe(WDSubscriptionRequest subscriptionRequest) {
            super("session.subscribe", subscriptionRequest);
        }
    }

    public static class Unsubscribe extends WDCommandImpl<UnsubscribeParameters> implements WDCommandData {
        public Unsubscribe(List<String> events) {
            super("session.unsubscribe", new UnsubscribeParameters.WDUnsubscribeByAttributesRequestParams(events));
        }
        public Unsubscribe(UnsubscribeParameters unsubscribeParameters) {
            super("session.unsubscribe", unsubscribeParameters);
        }
    }
}