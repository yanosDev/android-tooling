rootProject.name = "android-tooling"

include(":toolingapp")

include(":styleguide")
include(":live-templates")
include(":lint-annotation")
include(":file-templates") // Enable this again when published to maven
include(":lint") // Enable this again when published to maven

project(":lint-annotation").projectDir = file("lint/annotation")

pluginManagement {
    //includeBuild("file-templates") // Replace with id("de.yanosdev.yd-file-templates") version "1.0.0" when published to maven
    //includeBuild("lint") // Replace with id("de.yanosdev.yd-lint") version "1.0.0" when published to maven
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
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}