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

    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
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