# buildSrc

Convention plugins and shared build utilities for the android-tooling monorepo.

## Contents

| File                               | Purpose                                                      |
|------------------------------------|--------------------------------------------------------------|
| `util/KotlinCompilerOptionsExt.kt` | Extension functions for configuring Kotlin compiler warnings |
| `util/ProjectExt.kt`               | Project-level extension utilities                            |

The version catalog at `../gradle/libs.versions.toml` is shared across all modules via `versionCatalogs` in each
module's `settings.gradle.kts`.