plugins {
    alias(libs.plugins.maven.vanniktech)
    id(libs.plugins.lint.get().pluginId)
    `kotlin-dsl`
}

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
            id = "io.github.yanosdev.lint"
            implementationClass = "de.yanosdev.lint.plugin.LintPlugin"
        }
    }
}

tasks.withType<Jar>().configureEach {
    manifest {
        attributes["Lint-Registry-v2"] = "de.yanosdev.lint.YanosDevIssueRegistry"
    }
}

mavenPublishing {
    publishToMavenCentral()
    signAllPublications()

    coordinates("io.github.yanosdev", "lint", libs.versions.yd.lint.get())

    pom {
        name = "YanosDev Lint"
        description = "Custom lint rules for YanosDev projects"
        url = "https://github.com/yanosdev/android-tooling"

        licenses {
            license {
                name = "The Apache License, Version 2.0"
                url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
            }
        }
        developers {
            developer {
                id = "yanosdev"
                name = "YanosDev"
            }
        }
        scm {
            connection = "scm:git:github.com/yanosdev/android-tooling.git"
            developerConnection = "scm:git:ssh://github.com/yanosdev/android-tooling.git"
            url = "https://github.com/yanosdev/android-tooling"
        }
    }
}