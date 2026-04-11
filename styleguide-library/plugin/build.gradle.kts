plugins {
    `kotlin-dsl`
    `maven-publish`
}

group = "de.yanosdev"
version = "1.0.0"

gradlePlugin {
    plugins {
        create("styleguidePlugin") {
            id = "de.yanosdev.styleguide"
            implementationClass = "de.yanosdev.styleguide.plugin.StyleGuidePlugin"
        }
    }
}

dependencies {
    compileOnly(gradleApi())
}

publishing {
    publications {
        create<MavenPublication>("pluginMaven") {
            groupId = "de.yanosdev"
            artifactId = "styleguide-plugin"
            version = "1.0.0"
        }
    }
}
