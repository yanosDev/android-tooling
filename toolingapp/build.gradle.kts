plugins {
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.compose.compiler.get().pluginId)
    alias(libs.plugins.kotlin.serialization)

}

android {
    namespace = "de.yanosdev.tooling"
    compileSdk = libs.versions.yd.targetSdk.get().toInt()

    defaultConfig {
        applicationId = "de.yanosdev.tooling"
        minSdk = libs.versions.yd.minSdk.get().toInt()
        versionName = "1.0.0"

    }

    buildFeatures {
        buildConfig = true
        compose = true
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":lint-annotation"))
    lintChecks(project(":lint"))
    implementation(project(":styleguide"))

    // AndroidX
    implementation(libs.bundles.androidx.app)
    implementation(libs.bundles.androidx.navigation3)

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.app)
    debugImplementation(libs.androidx.compose.ui.tooling)
}