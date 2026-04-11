import de.yanosdev.buildsrc.dependency.YDVersion

plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.compose.compiler.get().pluginId)
    `maven-publish`
}

android {
    namespace = "de.yanosdev.styleguide"
    compileSdk = YDVersion.AndroidTargetSdk

    defaultConfig {
        minSdk = YDVersion.AndroidMinSdk
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }

    buildFeatures { compose = true }
}

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.material3)
    debugImplementation(libs.compose.ui.tooling)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                groupId = "de.yanosdev"
                artifactId = "styleguide"
                version = "1.0.0"
                from(components["release"])
            }
        }
    }
}
