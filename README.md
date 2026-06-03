# wd4j

[![Maven Central](https://img.shields.io/maven-central/v/com.aresstack/wd4j.svg)](https://central.sonatype.com/artifact/com.aresstack/wd4j)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

`wd4j` is a lightweight Java 8 library for the [W3C WebDriver BiDi](https://www.w3.org/TR/webdriver-bidi/) protocol. It provides typed command/request models, result models, event dispatching, and WebSocket client helpers for applications that want direct browser automation over a WebDriver BiDi endpoint.

The project intentionally stays close to the protocol. It is useful when you need low-level BiDi access without pulling in a larger automation framework.

## Installation

### Gradle

```groovy
implementation 'com.aresstack:wd4j:0.1.0-beta.1'
```

### Maven

```xml
<dependency>
    <groupId>com.aresstack</groupId>
    <artifactId>wd4j</artifactId>
    <version>0.1.0-beta.1</version>
</dependency>
```

## Status

`0.1.0-beta.1` is the first Maven Central beta release. The public API is usable, but it should still be treated as a beta-level protocol binding while WebDriver BiDi support continues to evolve across browsers and drivers.

The library targets Java 8 bytecode. The Gradle build can run on newer JDKs and uses `--release 8` when the current compiler supports it.

## What is included

- Typed request and result models for WebDriver BiDi modules.
- WebSocket transport helpers based on `Java-WebSocket`.
- Gson-based protocol serialization and deserialization.
- Event dispatching for BiDi events.
- A small `WebDriver` facade that groups the browser, session, browsing context, script, input, storage, network, log, and web extension managers.
- A per-browsing-context navigation queue that serializes rapid `browsingContext.navigate` calls.
- SLF4J-based library logging without bundling a logging backend.

## Basic usage

WD4J expects an existing WebDriver BiDi WebSocket endpoint. Applications are responsible for starting the browser or driver process and passing the endpoint URI to the library.

```java
import com.aresstack.WDWebSocketImpl;
import com.aresstack.WebDriver;
import com.aresstack.command.response.WDBrowsingContextResult;
import com.aresstack.service.WDEventDispatcher;
import com.aresstack.support.navigation.WDNavigationQueue;
import com.aresstack.support.navigation.WDNavigationQueues;
import com.aresstack.type.browsingContext.WDReadinessState;

import java.net.URI;

public class Wd4jExample {

    public static void main(String[] args) throws Exception {
        URI bidiEndpoint = URI.create("ws://127.0.0.1:9222/session/example");

        WDWebSocketImpl webSocket = new WDWebSocketImpl(bidiEndpoint);
        WDEventDispatcher dispatcher = new WDEventDispatcher();
        WDNavigationQueue navigationQueue = WDNavigationQueues.serial(100L);

        WebDriver driver = new WebDriver(webSocket, dispatcher, navigationQueue);

        WDBrowsingContextResult.CreateResult context = driver.browsingContext().create();
        driver.browsingContext().navigate(
                "https://example.org",
                context.getContext(),
                WDReadinessState.INTERACTIVE
        );
    }
}
```

The endpoint format depends on the browser and driver setup. For Chrome/Chromium integrations, WD4J has also been used through the `MappingTab` bridge.

## Logging

WD4J uses the Simple Logging Facade for Java (SLF4J). The library depends on `slf4j-api` only and does not ship Log4J or a `logback.xml` file. Applications should provide their own runtime backend; Logback is the recommended default.

Gradle example:

```groovy
implementation 'com.aresstack:wd4j:0.1.0-beta.1'
runtimeOnly 'ch.qos.logback:logback-classic:1.2.13'
```

Maven example:

```xml
<dependency>
    <groupId>com.aresstack</groupId>
    <artifactId>wd4j</artifactId>
    <version>0.1.0-beta.1</version>
</dependency>
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.2.13</version>
    <scope>runtime</scope>
</dependency>
```

Useful logger names:

```text
com.aresstack
com.aresstack.WDWebSocketImpl
com.aresstack.WDWebSocketManagerImpl
com.aresstack.manager.WDSessionManager
com.aresstack.service.WDEventDispatcher
```

Set `com.aresstack.WDWebSocketImpl` to `TRACE` when you need to inspect WebSocket payload flow.

## Navigation queue

`WDBrowsingContextManager.navigate(...)` routes `browsingContext.navigate` commands through a per-context navigation queue by default. This protects compatibility-sensitive browser/driver combinations from rapid navigation bursts while still allowing different browsing contexts to navigate independently.

Default behavior:

```bash
-Dwd4j.navigation.queue.enabled=true
-Dwd4j.navigation.queue.quietPeriodMs=0
```

For Firefox/GeckoDriver scenarios that react badly to rapid consecutive navigations, add a small quiet period between navigation commands for the same context:

```bash
-Dwd4j.navigation.queue.quietPeriodMs=100
```

For custom integration code, inject a queue explicitly:

```java
WDNavigationQueue navigationQueue = WDNavigationQueues.serial(100L);
WebDriver driver = new WebDriver(webSocket, dispatcher, navigationQueue);
```

Disable the queue only when the caller already guarantees browser-compatible navigation pacing:

```bash
-Dwd4j.navigation.queue.enabled=false
```

## Browser compatibility notes

WebDriver BiDi is still evolving, and browser/driver implementations can differ. WD4J keeps the wire model close to the W3C protocol, but application code should still expect browser-specific behavior.

### Chrome / Chromium

WD4J has been used successfully with Chrome/Chromium through the `MappingTab` integration. In that setup, rapid consecutive navigations did not show the same browser-freeze behavior that was observed with Firefox.

### Firefox / GeckoDriver

Known compatibility observations from development:

- `session.unsubscribe` can behave differently depending on the Firefox/GeckoDriver version. WD4J first tries unsubscribe by subscription ID and falls back to unsubscribe by attributes when the ID-based request fails.
- The W3C draft defines `session.unsubscribe` as a union of unsubscribe-by-ID and unsubscribe-by-attributes. The ID variant uses `subscriptions`; the attributes variant currently uses `events`. WD4J keeps a deprecated optional `contexts` field for compatibility with older implementations.
- Firefox was observed to hang when many navigations were sent in rapid succession. WD4J serializes `browsingContext.navigate` commands per browsing context by default. For sensitive Firefox/GeckoDriver setups, configure `-Dwd4j.navigation.queue.quietPeriodMs=100` and consider using `WDReadinessState.NONE` or `WDReadinessState.INTERACTIVE` when full page-load waiting is not required.

## Build from source

```bash
bash ./gradlew clean build
```

For the ChatGPT-compatible release package, use the offline build script included in the ZIP:

```bash
bash ./chatgpt-build.sh
```

## Publish locally

```bash
bash ./gradlew publishToMavenLocal
```

For a local staging repository under `build/staging-deploy`:

```bash
bash ./gradlew publish
```

The Gradle build creates the normal JAR, sources JAR, Javadoc JAR, Maven POM, and optional signatures for non-SNAPSHOT release builds.

## Runtime dependencies

- `com.google.code.gson:gson:2.8.9`
- `org.slf4j:slf4j-api:1.7.32`
- `org.java-websocket:Java-WebSocket:1.5.2`
- `org.reflections:reflections:0.10.2`

## License

WD4J is released under the MIT License. See [LICENSE](LICENSE).
