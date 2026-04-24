plugins {
    id(libs.plugins.android.library.get().pluginId) apply false
    alias(libs.plugins.yd.lint)
    alias(libs.plugins.yd.file.templates)
    alias(libs.plugins.yd.live.templates)
}

tasks.register("publishAll") {
    group = "publishing"
    description = "Publishes all libraries"
    dependsOn(
        ":styleguide:publish",
        ":file-templates:publish",
        ":live-templates:publish",
        ":lint:publish"
    )
    finalizedBy("syncReadmeVersions")
}

tasks.register("syncReadmeVersions") {
    group = "documentation"
    description = "Updates version placeholders in README.md from the version catalog"
    doLast {
        val versions = mapOf(
            "yd-styleguide" to libs.versions.yd.styleguide.get(),
            "yd-lint" to libs.versions.yd.lint.get(),
            "yd-file-templates" to libs.versions.yd.file.templates.get(),
            "yd-live-templates" to libs.versions.yd.live.templates.get(),
        )
        val readme = file("README.md")
        var text = readme.readText()
        versions.forEach { (key, version) ->
            text = text.replace(
                Regex("(<!-- version:$key -->)[^<]*(<!-- /version:$key -->)"),
                "\$1$version\$2"
            )
        }
        readme.writeText(text)
        println("Synced versions: $versions")
    }
}