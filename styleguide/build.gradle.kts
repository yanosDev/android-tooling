plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.compose.compiler.get().pluginId)
}


val artifactId = "styleguide"

group = "de.yanosdev"
version = libs.versions.yd.styleguide.get()

android {
    namespace = "de.yanosdev.styleguide"
    compileSdk = libs.versions.yd.targetSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.yd.minSdk.get().toInt()
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
        languageVersion = JavaLanguageVersion.of(libs.versions.yd.java.get())
    }
}

dependencies {
    // Lint
    implementation(project(":lint-annotation"))
    lintChecks(project(":lint"))

    // AndroidX
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    debugImplementation(libs.androidx.compose.ui.tooling)

    // Material
    api(libs.androidx.compose.material3.wsc)
    implementation(libs.androidx.compose.material3)

}