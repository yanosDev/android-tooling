plugins {
    `kotlin-dsl`
    `maven-publish`
}

group = "de.yanosdev"
version = "1.0.0"

gradlePlugin {
    plugins {
        create("fileTemplatesPlugin") {
            id = "de.yanosdev.file-templates"
            implementationClass = "de.yanosdev.filetemplates.plugin.FileTemplatesPlugin"
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("pluginMaven") {
            groupId = "de.yanosdev"
            artifactId = "file-templates-plugin"
            version = "1.0.0"
        }
    }
}
