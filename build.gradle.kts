plugins {
    id(libs.plugins.android.library.get().pluginId) apply false

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