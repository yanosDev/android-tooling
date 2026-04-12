plugins {
    id(libs.plugins.android.library.get().pluginId) apply false
    //  id("de.yanosdev.FileTemplatesPlugin")
//    id("de.yanosdev.LintPlugin")

//    id("de.yanosdev.file-templates") version "1.0.0" // How to automatically import file templates
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