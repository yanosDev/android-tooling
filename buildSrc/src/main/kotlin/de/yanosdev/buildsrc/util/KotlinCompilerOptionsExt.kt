package de.yanosdev.buildsrc.util


import org.jetbrains.kotlin.gradle.dsl.KotlinCommonCompilerOptions

fun KotlinCommonCompilerOptions.configureWarnings(strict: Boolean) {
    allWarningsAsErrors.set(strict)
    extraWarnings.set(true)

    freeCompilerArgs.add("-Xwarning-level=ASSIGNED_VALUE_IS_NEVER_READ:warning")
}

fun KotlinCommonCompilerOptions.suppressGeneratedCodeWarnings() {
    freeCompilerArgs.add("-Xwarning-level=REDUNDANT_VISIBILITY_MODIFIER:disabled")
}