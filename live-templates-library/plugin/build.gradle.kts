plugins {
    `kotlin-dsl`
    `maven-publish`
}

group = "de.yanosdev"
version = "1.0.0"

gradlePlugin {
    plugins {
        create("liveTemplatesPlugin") {
            id = "de.yanosdev.live-templates"
            implementationClass = "de.yanosdev.livetemplates.plugin.LiveTemplatesPlugin"
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("pluginMaven") {
            groupId = "de.yanosdev"
            artifactId = "live-templates-plugin"
            version = "1.0.0"
        }
    }
}
