plugins {
    `kotlin-dsl`
    `maven-publish`
}

val artifactId = "live-templates"
val VERSION = libs.versions.yd.lint.get()

group = "de.yanosdev"
version = libs.versions.yd.lint.get()

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                groupId = "de.yanosdev"
                artifactId = artifactId
                version = version
                from(components["java"])
            }
        }
    }
}