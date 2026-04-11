import de.yanosdev.buildsrc.dependency.YDVersion

plugins {
    id("yd-android-library")
    id(libs.plugins.compose.compiler.get().pluginId)
}

publishingConfig {
    artifactId = "styleguide-library"
    version = YDVersion.StyleGuideVersion
    component = "release"
}

android {
    namespace = "de.yanosdev.styleguide"
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

dependencies {
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