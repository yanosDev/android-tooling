plugins {
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.android.library) apply false
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
