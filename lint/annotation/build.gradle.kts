import de.yanosdev.buildsrc.util.allWarningsAsErrorsConfig
import de.yanosdev.buildsrc.util.configureWarnings

plugins {
    id(libs.plugins.android.library.get().pluginId)
}

android {
    namespace = "de.yanosdev.annotation"

    compileSdk = libs.versions.yd.targetSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.yd.minSdk.get().toInt()
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(libs.versions.yd.java.get())
    }
}

kotlin {
    compilerOptions {
        configureWarnings(strict = allWarningsAsErrorsConfig)
    }
}