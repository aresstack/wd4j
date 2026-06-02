package com.aresstack.command.request;

import com.aresstack.api.markerInterfaces.WDCommandData;
import com.aresstack.command.request.helper.WDCommandImpl;
import com.aresstack.command.request.parameters.webExtension.ExtensionData;
import com.aresstack.command.request.parameters.webExtension.InstallParameters;
import com.aresstack.command.request.parameters.webExtension.UninstallParameters;
import com.aresstack.type.webExtension.WDExtension;

public class WDWebExtensionRequest {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Commands (Classes)
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class Install extends WDCommandImpl<InstallParameters> implements WDCommandData {
        public Install(ExtensionData extensionData) {
            super("webExtension.install", new InstallParameters(extensionData));
        }
    }

    public static class Uninstall extends WDCommandImpl<UninstallParameters> implements WDCommandData {
        public Uninstall(WDExtension WDExtension) {
            super("webExtension.uninstall", new UninstallParameters(WDExtension));
        }
    }

}