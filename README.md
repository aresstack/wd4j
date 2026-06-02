# wd4j

`wd4j` contains the standalone Java 8 WebDriver BiDi bindings and WebSocket client helpers extracted from the `MainframeMate` Gradle multi-project build.

## Build

```bash
./gradlew clean build
```

The project targets Java 8 bytecode. When Gradle runs on Java 9 or newer, compilation uses `--release 8`.

## Coordinates

Default Gradle coordinates are defined in `gradle.properties`:

```properties
projectGroup=de.bund.zrb
projectVersion=5.4.0-SNAPSHOT
artifactId=wd4j
```

Change `projectGroup` before publishing if the Maven Central namespace is different.

## Publish locally

```bash
./gradlew publishToMavenLocal
```

For a local staging repository under `build/staging-deploy`:

```bash
./gradlew publish
```

The Gradle build already creates the normal JAR, sources JAR, Javadoc JAR, Maven POM, and optional signatures for non-SNAPSHOT `publish` builds.

## Runtime dependencies

- `com.google.code.gson:gson:2.8.9`
- `org.java-websocket:Java-WebSocket:1.5.2`
- `org.reflections:reflections:0.10.2`

## License

MIT License. See `LICENSE`.
