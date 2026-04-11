plugins {
    id(libs.plugins.android.library.get().pluginId) apply false
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
