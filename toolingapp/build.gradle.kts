plugins {
    id("yd-android-application")
}

android {
    namespace = "de.yanosdev.tooling"

    defaultConfig {
        applicationId = "de.yanosdev.tooling"
        versionName = "1.0.0"

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(project(":lint-library"))
    implementation(project(":styleguide-library"))

    // AndroidX
    implementation(libs.bundles.androidx.app)
    implementation(libs.bundles.androidx.navigation3)

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.app)
    debugImplementation(libs.androidx.compose.ui.tooling)
}