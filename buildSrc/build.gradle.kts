plugins {
    `kotlin-dsl`
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

kotlin {
    compilerOptions {
        allWarningsAsErrors = true
        extraWarnings = true
        optIn.add("kotlin.time.ExperimentalTime")
        // The generated sources for our convention plugins contain redundant visibility modifiers since Gradle 9.0.0
        freeCompilerArgs.add("-Xwarning-level=REDUNDANT_VISIBILITY_MODIFIER:disabled")
    }
}

dependencies {
    implementation(libs.compose.compiler.plugin)
    implementation(libs.android.gradle.plugin)
    implementation(libs.kotlin.gradle.plugin)

    // Code Generation
    // implementation(libs.kotlinpoet)
}
