import de.yanosdev.buildsrc.dependency.YDVersion

plugins {
    id("com.android.library")
    id("yd-publisher")
}

android {
    compileSdk = YDVersion.AndroidTargetSdk

    defaultConfig {
        minSdk = YDVersion.AndroidMinSdk
    }
}
