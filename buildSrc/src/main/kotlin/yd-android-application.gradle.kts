import de.yanosdev.buildsrc.dependency.YDVersion
import de.yanosdev.buildsrc.util.allWarningsAsErrorsConfig
import de.yanosdev.buildsrc.util.configureWarnings
import de.yanosdev.buildsrc.util.enableComposeCompilerReportsConfig
import de.yanosdev.buildsrc.util.envVersionCode
import de.yanosdev.buildsrc.util.toggleComposeCompilerReports

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    compileSdk = YDVersion.AndroidTargetSdk

    defaultConfig {
        minSdk = YDVersion.AndroidMinSdk

        versionCode = envVersionCode
    }

    buildTypes {
        debug {
            isDebuggable = true
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = true
        }
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }

    lint {
        abortOnError = false
        if (project.hasProperty("disableCheckReleaseBuilds")) {
            checkReleaseBuilds = false
        }
    }
}

java {
    toolchain {
        languageVersion = YDVersion.Java
    }
}

kotlin {
    compilerOptions {
        configureWarnings(strict = allWarningsAsErrorsConfig)
        optIn.add("kotlin.time.ExperimentalTime")
        freeCompilerArgs.add("-Xannotation-default-target=param-property")
        toggleComposeCompilerReports(enableComposeCompilerReportsConfig, this)
    }
}

dependencies {
    implementation(project(":styleguide-library"))

    // Lint
    implementation(project(":lint-library:annotation"))
    lintChecks(project(":lint-library"))
}