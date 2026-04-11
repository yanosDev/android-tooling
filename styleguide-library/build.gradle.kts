import de.yanosdev.buildsrc.dependency.YDVersion

plugins {
    id("yd-android-library")
    id(libs.plugins.compose.compiler.get().pluginId)
}

publishingConfig {
    artifactId = "styleguide"
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
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.material3)
    debugImplementation(libs.compose.ui.tooling)
}