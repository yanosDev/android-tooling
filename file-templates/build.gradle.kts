plugins {
    `maven-publish`
    `kotlin-dsl`
}

val artifactId = "file-templates"

group = "de.yanosdev"
version = libs.versions.yd.file.templates.get()

gradlePlugin {
    plugins {
        create("LintFileTemplatesPlugin") {
            id = "de.yanosdev.LintFileTemplatesPlugin"
            implementationClass = "de.yanosdev.filetemplates.plugin.FileTemplatesPlugin"
        }
    }
}