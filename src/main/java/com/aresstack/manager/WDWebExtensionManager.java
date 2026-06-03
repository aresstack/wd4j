package com.aresstack.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aresstack.api.markerInterfaces.WDModule;
import com.aresstack.command.request.WDWebExtensionRequest;
import com.aresstack.command.request.parameters.webExtension.ExtensionData;
import com.aresstack.command.response.WDWebExtensionResult;
import com.aresstack.type.webExtension.WDExtension;
import com.aresstack.api.WDWebSocketManager;

import com.aresstack.command.response.WDEmptyResult;

public class WDWebExtensionManager implements WDModule {
    private static final Logger LOGGER = LoggerFactory.getLogger(WDWebExtensionManager.class);


    private final WDWebSocketManager WDWebSocketManager;

    public WDWebExtensionManager(WDWebSocketManager WDWebSocketManager) {
        this.WDWebSocketManager = WDWebSocketManager;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Event Handlers
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Commands
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Installs a web extension in the specified browsing context.
     *
     * @param extensionData The extension of a specific type to install. See {@link ExtensionData} for more information.
     * @return The installed extension result.
     * @throws RuntimeException if the installation fails.
     */
    public WDWebExtensionResult.InstallResult install(ExtensionData extensionData) {
        try {
            WDWebExtensionResult.InstallResult result = WDWebSocketManager.sendAndWaitForResponse(
                    new WDWebExtensionRequest.Install(extensionData), WDWebExtensionResult.InstallResult.class
            );
            LOGGER.debug("Web extension installed: {}", result.getExtension().value());
            return result;
        } catch (RuntimeException e) {
            LOGGER.warn("Error installing web extension.", e);
            throw e;
        }
    }

    /**
     * Uninstalls a web extension from the specified browsing context.
     *
     * @param extension The extension to uninstall.
     * @throws RuntimeException if the uninstallation fails.
     */
    public void uninstall(WDExtension extension) {
        try {
            WDWebSocketManager.sendAndWaitForResponse(
                    new WDWebExtensionRequest.Uninstall(extension), WDEmptyResult.class
            );
            LOGGER.debug("Web extension uninstalled: {}", extension.value());
        } catch (RuntimeException e) {
            LOGGER.warn("Error uninstalling web extension.", e);
            throw e;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Events (Classes)
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}