import de.yanosdev.buildsrc.dependency.YDVersion
import de.yanosdev.buildsrc.util.allWarningsAsErrorsConfig
import de.yanosdev.buildsrc.util.configureWarnings


plugins {
    id("com.android.library")
}

android {
    namespace = "de.yanosdev.annotation"

    compileSdk = YDVersion.AndroidTargetSdk

    defaultConfig {
        minSdk = YDVersion.AndroidMinSdk
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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
    }
}