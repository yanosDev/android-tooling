plugins {
    `maven-publish`
    `kotlin-dsl`
}

val artifactId = "file-templates"

group = "de.yanosdev"
version = libs.versions.yd.lint.get()

gradlePlugin {
    plugins {
        create("FileTemplatesPlugin") {
            id = "de.yanosdev.FileTemplatesPlugin"
            implementationClass = "de.yanosdev.filetemplates.plugin.FileTemplatesPlugin"
        }
    }
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                groupId = "de.yanosdev"
                artifactId = artifactId
                version = libs.versions.yd.lint.get()
                from(components["java"])
            }
        }
    }
}