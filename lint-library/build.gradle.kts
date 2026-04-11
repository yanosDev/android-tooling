plugins {
    id("android-tooling-base")
}

dependencies {
    compileOnly(libs.lint.api)
    compileOnly(libs.lint.checks)
    testImplementation(libs.lint.tests)
    testImplementation(libs.junit)
}

// Required so Android tooling picks up the lint jar
tasks.withType<Jar>().configureEach {
    manifest {
        attributes["Lint-Registry-v2"] = "de.yanosdev.lint.YanosDevIssueRegistry"
    }
}
publishingConfig {
    artifactId = "lint"
    version = "1.0.0"
}