plugins {
    `maven-publish`
    `kotlin-dsl`
}

group = "de.yanosdev"
version = libs.versions.yd.file.templates.get()

gradlePlugin {
    plugins {
        create("FileTemplatesPlugin") {
            id = "de.yanosdev.FileTemplatesPlugin"
            implementationClass = "de.yanosdev.filetemplates.plugin.FileTemplatesPlugin"
        }
    }
}