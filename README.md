# wd4j

`wd4j` contains standalone Java 8 WebDriver BiDi bindings and WebSocket client helpers extracted from the `MainframeMate` Gradle multi-project build.

The library is implemented against the W3C WebDriver BiDi specification:

<https://www.w3.org/TR/webdriver-bidi/>

## Build

```bash
bash ./gradlew clean build
```

For the ChatGPT-compatible release package, use the offline build script included in the ZIP:

```bash
bash ./chatgpt-build.sh
```

The project targets Java 8 bytecode. The Gradle build can run on a newer JDK and uses `--release 8` when the current compiler supports it.

## Coordinates

Default Gradle coordinates are defined in `gradle.properties`:

```properties
projectGroup=com.aresstack
projectVersion=5.4.0-SNAPSHOT
artifactId=wd4j
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
- `org.java-websocket:Java-WebSocket:1.5.2`
- `org.reflections:reflections:0.10.2`


## Navigation queue

`WDBrowsingContextManager.navigate(...)` now routes `browsingContext.navigate` commands through a per-context navigation queue by default. This keeps navigation commands for the same browsing context serialized while still allowing different browsing contexts to navigate independently.

The default queue is intentionally small and compatibility-focused:

```bash
-Dwd4j.navigation.queue.enabled=true
-Dwd4j.navigation.queue.quietPeriodMs=0
```

For Firefox/GeckoDriver scenarios that react badly to rapid navigation bursts, add a small quiet period between navigation commands for the same context:

```bash
-Dwd4j.navigation.queue.quietPeriodMs=100
```

For custom integration code, inject a queue explicitly:

```java
WDNavigationQueue navigationQueue = WDNavigationQueues.serial(100L);
WebDriver driver = new WebDriver(webSocket, dispatcher, navigationQueue);
```

Set `wd4j.navigation.queue.enabled=false` only when the caller already guarantees browser-compatible navigation pacing.

## Browser compatibility notes

WebDriver BiDi is still a moving target across browser implementations. WD4J keeps the wire model close to the W3C protocol, but browser-specific behavior can still differ.

### Chrome / Chromium

WD4J has been used successfully with Chrome through the `MappingTab` integration. In that setup, rapid consecutive navigations did not show the same browser-freeze behavior that was observed with Firefox.

### Firefox / GeckoDriver

Known limitations observed during development:

- `session.unsubscribe` can be problematic in Firefox/GeckoDriver depending on the exact browser and driver version. WD4J first tries unsubscribe by subscription ID and falls back to unsubscribe by attributes when the ID-based request fails.
- The current W3C draft defines `session.unsubscribe` as a union of unsubscribe-by-ID and unsubscribe-by-attributes. The ID variant uses `subscriptions`; the attributes variant currently uses `events`. WD4J still contains a deprecated optional `contexts` field for compatibility with older implementations.
- Firefox was observed to hang during navigation when many navigations were sent in rapid succession. WD4J now serializes `browsingContext.navigate` commands per browsing context by default. For especially sensitive Firefox/GeckoDriver setups, configure `-Dwd4j.navigation.queue.quietPeriodMs=100` and consider using `WDReadinessState.NONE` or `WDReadinessState.INTERACTIVE` when full page-load waiting is not required.

## License

MIT License. See `LICENSE`.
