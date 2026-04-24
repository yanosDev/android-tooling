plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.compose.compiler.get().pluginId)
    `maven-publish`
}

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

    buildTypes {
        debug {
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = true
        }
    }

    lint {
        abortOnError = false
        if (project.hasProperty("disableCheckReleaseBuilds")) {
            checkReleaseBuilds = false
        }
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(libs.versions.yd.java.get())
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":lint-annotation"))
    lintChecks(project(":lint"))

    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.icon)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)

    api(libs.androidx.compose.material3.wsc)
    implementation(libs.androidx.compose.material3)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                groupId = "de.yanosdev"
                artifactId = "styleguide"
                version = libs.versions.yd.styleguide.get()
                from(components["release"])
            }
        }
    }
}