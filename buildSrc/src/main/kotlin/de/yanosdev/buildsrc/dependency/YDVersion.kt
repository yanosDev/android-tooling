package de.yanosdev.buildsrc.dependency

import org.gradle.jvm.toolchain.JavaLanguageVersion

object YDVersion {
    val Java = JavaLanguageVersion.of(21)

    const val AndroidMinSdk = 28
    const val AndroidTargetSdk = 36
    const val StyleGuideVersion = "1.0.0"
    const val LiveTemplatesVersion = "1.0.0"
    const val FileTemplatesVersion = "1.0.0"
    const val LintVersion = "1.0.0"
}