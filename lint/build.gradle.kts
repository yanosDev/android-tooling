plugins {
    id(libs.plugins.lint.get().pluginId)
    `kotlin-dsl`
    `maven-publish`
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
            id = "de.yanosdev.LintPlugin"
            implementationClass = "de.yanosdev.lint.plugin.LintPlugin"
        }
    }
}

tasks.withType<Jar>().configureEach {
    manifest {
        attributes["Lint-Registry-v2"] = "de.yanosdev.lint.YDIssueRegistry"
    }
}