plugins {
    id(libs.plugins.android.library.get().pluginId) apply false
    alias(libs.plugins.yd.live.templates)
    alias(libs.plugins.yd.file.templates)
    alias(libs.plugins.yd.lint)
}

// Root-level tasks
tasks.register("publishAll") {
    group = "publishing"
    description = "Publishes all libraries"
    dependsOn(
        ":styleguide:publish",
        ":file-templates:publish",
        ":live-templates:publish",
        ":lint:publish"
    )
}