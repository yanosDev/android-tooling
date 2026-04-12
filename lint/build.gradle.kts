plugins {
    alias(libs.plugins.lint)
    `maven-publish`
    `kotlin-dsl`
}

val artifactId = "lint"

group = "de.yanosdev"
version = libs.versions.yd.lint.get()

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(libs.versions.yd.java.get())
    }
}

dependencies {
    compileOnly(libs.bundles.android.lint)
    testImplementation(libs.android.lint.api)
    testImplementation(libs.bundles.test.core)
    testImplementation(libs.android.lint.tests)
}

gradlePlugin {
    plugins {
        create("LintPlugin") {
            id = "de.yanosdev.LintPlugin"
            implementationClass = "de.yanosdev.lint.plugin.LintPlugin"
        }
    }
}

// Required so Android tooling picks up the lint jar
tasks.withType<Jar>().configureEach {
    manifest {
        attributes["Lint-Registry-v2"] = "de.yanosdev.lint.YanosDevIssueRegistry"
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