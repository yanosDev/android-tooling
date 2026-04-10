# Android Tooling – Monorepo

A suite of Android developer tooling libraries distributed via JitPack.

## Libraries

| Library | Plugin ID | Artifact |
|---|---|---|
| StyleGuide | `de.yanodev.styleguide` | `de.yanodev:styleguide:1.0.0` |
| File Templates | `de.yanodev.file-templates` | `de.yanodev:file-templates:1.0.0` |
| Live Templates | `de.yanodev.live-templates` | `de.yanodev:live-templates:1.0.0` |
| Lint Rules | `de.yanodev.lint` | `de.yanodev:lint:1.0.0` |

---

## Setup in a consumer project

### 1. Add JitPack to `settings.gradle.kts`
```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")          // ← add this
    }
    // also add plugin repos if using plugins block:
    pluginManagement {
        repositories {
            gradlePluginPortal()
            maven("https://jitpack.io")
        }
    }
}
```

### 2. Apply individual plugins in your module's `build.gradle.kts`
```kotlin
plugins {
    id("de.yanodev.styleguide")     version "1.0.0"
    id("de.yanodev.lint")           version "1.0.0"
    id("de.yanodev.file-templates") version "1.0.0"
    id("de.yanodev.live-templates") version "1.0.0"
}
// Each plugin automatically adds its library dependency – nothing else needed.
```

---

## Publishing to JitPack

JitPack builds directly from GitHub. Steps:
1. Push this repo to GitHub (e.g. `github.com/yourname/android-tooling`)
2. Create a Git tag: `git tag 1.0.0 && git push origin 1.0.0`
3. Open `https://jitpack.io/#yourname/android-tooling` – JitPack auto-builds on first request
4. Consumers use the artifact as shown above

No CI configuration is needed for JitPack; it uses your `build.gradle.kts` directly.

---

## Live Templates reference

| Abbreviation | Expands to |
|---|---|
| `comp` | `@Composable fun Name() { }` |
| `comppreview` | Composable + `@Preview` pair |
| `vm` | `ViewModel` class + `UiState` data class |
| `repo` | Repository interface + Impl class |
| `usecase` | `UseCase` with `runCatching` |
| `flowstate` | `collectAsStateWithLifecycle()` |

---

## Custom Lint Rules

| Rule ID | Severity | What it catches |
|---|---|---|
| `HardcodedColor` | Warning | `#RRGGBB` values in XML layouts |
| `MissingContentDescription` | Error | `ImageView` without `contentDescription` |

Run manually: `./gradlew lint`

---

## Adding a new Lint rule

1. Create `YourDetector.kt` in `lint-library/src/main/kotlin/de/yanosdev/lint/`
2. Register the `Issue` in `YanosDevIssueRegistry.issues`
3. Add a test in `lint-library/src/test/kotlin/de/yanosdev/lint/`
4. Bump `VERSION_NAME` in `gradle.properties` and tag a new release
