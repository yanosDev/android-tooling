plugins {
    `maven-publish`
}

val publishingConfig = extensions.create<PublishingConfig>("publishingConfig")

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                groupId = "de.yanosdev"
                artifactId = publishingConfig.artifactId
                version = publishingConfig.version
                from(components[publishingConfig.component])
            }
        }
    }
}