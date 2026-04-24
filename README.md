# Android Tooling – Monorepo [![](https://jitpack.io/v/yanosDev/android-tooling.svg)](https://jitpack.io/#yanosDev/android-tooling)

A suite of Android developer tooling libraries distributed via JitPack.

> Run `./gradlew syncReadmeVersions` after bumping versions in `gradle/libs.versions.toml` to keep this file in sync.

## Libraries

| Module           | Type                        | Version                                                                    |
|------------------|-----------------------------|----------------------------------------------------------------------------|
| `styleguide`     | Android library             | <!-- version:yd-styleguide -->0.0.0<!-- /version:yd-styleguide -->         |
| `lint`           | Gradle plugin + lint checks | <!-- version:yd-lint -->0.0.0<!-- /version:yd-lint -->                     |
| `file-templates` | Gradle plugin               | <!-- version:yd-file-templates -->0.0.0<!-- /version:yd-file-templates --> |
| `live-templates` | Gradle plugin               | <!-- version:yd-live-templates -->0.0.0<!-- /version:yd-live-templates --> |

---

## Setup in a consumer project

### 1. Configure repositories in `settings.gradle.kts`

```kotlin
pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://jitpack.io")
    }
    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "de.yanosdev.LintPlugin" -> useModule(
                    "com.github.yanosDev.android-tooling:lint:${requested.version}"
                )
                "de.yanosdev.FileTemplatesPlugin" -> useModule(
                    "com.github.yanosDev.android-tooling:file-templates:${requested.version}"
                )
                "de.yanosdev.LiveTemplatesPlugin" -> useModule(
                    "com.github.yanosDev.android-tooling:live-templates:${requested.version}"
                )
            }
        }
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}
```

### 2. Apply Gradle plugins in your root `build.gradle.kts`

```kotlin
plugins {
    id("de.yanosdev.LintPlugin") version "<!-- version:yd-lint -->0.0.0<!-- /version:yd-lint -->"
    id("de.yanosdev.FileTemplatesPlugin") version "<!-- version:yd-file-templates -->0.0.0<!-- /version:yd-file-templates -->" apply false
    id("de.yanosdev.LiveTemplatesPlugin") version "<!-- version:yd-live-templates -->0.0.0<!-- /version:yd-live-templates -->" apply false
}
```

### 3. Add the styleguide library

```kotlin
// in your module's build.gradle.kts
dependencies {
    implementation("com.github.yanosDev.android-tooling:styleguide:<!-- version:yd-styleguide -->0.0.0<!-- /version:yd-styleguide -->")
    lintChecks("com.github.yanosDev.android-tooling:lint:<!-- version:yd-lint -->0.0.0<!-- /version:yd-lint -->")
}
```

---

## Publishing a new release

1. Bump versions in `gradle/libs.versions.toml`
2. Run `./gradlew syncReadmeVersions` to update this README, then commit
3. Create a Git tag and push:
   ```bash
   git tag 0.0.1 && git push origin 0.0.1
   ```
4. JitPack builds automatically on first consumer request — check status at
   `https://jitpack.io/#yanosDev/android-tooling`

---

## Custom Lint Rules

| Rule ID                        | Severity | What it catches                                                                                      |
|--------------------------------|----------|------------------------------------------------------------------------------------------------------|
| `YDComposableParameterSorting` | Error    | `@Composable` parameters not in required order (nav → VM → required → modifier → optional → content) |
| `DataClassParameterSorting`    | Warning  | `data class` parameters not alphabetically sorted (required before optional)                         |
| `NamedArgument`                | Warning  | Call-site args in `de.yanosdev.*` not using named arguments                                          |
| `YDRevisionMissing`            | Warning  | `.kt` files missing `@file:YDRevisionIn` annotation                                                  |
| `YDRevisionDue`                | Warning  | Files whose revision date has passed                                                                 |

See [`lint/documentation/`](lint/documentation/) for detailed rule documentation.

Run manually: `./gradlew lint`

---

## Live Templates

| Abbreviation  | Expands to                               |
|---------------|------------------------------------------|
| `comp`        | `@Composable fun Name() { }`             |
| `comppreview` | Composable + `@Preview` pair             |
| `vm`          | `ViewModel` class + `UiState` data class |
| `repo`        | Repository interface + Impl class        |
| `usecase`     | `UseCase` with `runCatching`             |
| `flowstate`   | `collectAsStateWithLifecycle()`          |

For file template installation, see [
`file-templates/FILE_TEMPLATES_README.md`](file-templates/FILE_TEMPLATES_README.md).