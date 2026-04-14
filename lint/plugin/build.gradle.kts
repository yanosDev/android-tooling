plugins {
    `kotlin-dsl`
    `maven-publish`
}

group = "de.yanosdev"
version = "1.0.0"

gradlePlugin {
    plugins {
        create("lintPlugin") {
            id = "de.yanosdev.lint"
            implementationClass = "de.yanosdev.lint.plugin.LintPlugin"
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("pluginMaven") {
            groupId = "de.yanosdev"
            artifactId = "lint-plugin"
            version = "1.0.0"
        }
    }
}
