import de.yanosdev.buildsrc.dependency.YDVersion

plugins {
    id("yd-android-library")
}

publishingConfig {
    artifactId = "annotation"
    version = YDVersion.AnnotationVersion
    component = "release"
}

android {
    namespace = "de.yanosdev.annotation"
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}