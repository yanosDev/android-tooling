import de.yanosdev.buildsrc.dependency.YDVersion
import de.yanosdev.buildsrc.util.allWarningsAsErrorsConfig
import de.yanosdev.buildsrc.util.configureWarnings

plugins {
    id("android-tooling-base")
}

publishingConfig {
    artifactId = "lint"
    version = YDVersion.LintVersion
}

java {
    toolchain {
        languageVersion = YDVersion.Java
    }
}


kotlin {
    compilerOptions {
        configureWarnings(strict = allWarningsAsErrorsConfig)
    }
}

dependencies {
    compileOnly(libs.bundles.android.lint)
    testImplementation(libs.android.lint.api)
    testImplementation(libs.bundles.test.core)
    testImplementation(libs.android.lint.tests)
}

// Required so Android tooling picks up the lint jar
tasks.withType<Jar>().configureEach {
    manifest {
        attributes["Lint-Registry-v2"] = "de.yanosdev.lint.YanosDevIssueRegistry"
    }
}