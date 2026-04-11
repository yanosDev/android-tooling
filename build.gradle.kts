plugins {
    id(libs.plugins.android.library.get().pluginId) apply false
//    id("de.yanosdev.file-templates") version "1.0.0"
}

// Root-level tasks
tasks.register("publishAll") {
    group = "publishing"
    description = "Publishes all libraries"
    dependsOn(
        ":styleguide-library:publish",
        ":file-templates-library:publish",
        ":live-templates-library:publish",
        ":lint-library:publish"
    )
}

// For local purpose since file templates are not yet in jitpack
tasks.named("prepareKotlinBuildScriptModel") {
    dependsOn(":file-templates-library:installFileTemplates")
}