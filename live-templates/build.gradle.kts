plugins {
    `kotlin-dsl`
    `maven-publish`
}

group = "de.yanosdev"
version = libs.versions.yd.live.templates.get()

gradlePlugin {
    plugins {
        create("LiveTemplatesPlugin") {
            id = "de.yanosdev.LiveTemplatesPlugin"
            implementationClass = "de.yanosdev.livetemplates.plugin.LiveTemplatesPlugin"
        }
    }
}