package de.yanosdev.buildsrc.util

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinCommonCompilerOptions
import java.util.Properties
import kotlin.io.inputStream
import kotlin.use


val Project.allWarningsAsErrorsConfig: Boolean
    get() = getBuildProperty("allWarningsAsErrors")
        ?.toBooleanStrictOrNull() ?: true

@Suppress("UnusedReceiverParameter")
val Project.envVersionCode: Int
    get() = System.getenv("VERSION_CODE")?.toInt() ?: 1000

val Project.enableComposeCompilerReportsConfig: Boolean
    get() = getBuildProperty("enableComposeCompilerReports")
        ?.toBooleanStrictOrNull() ?: false

fun Project.getBuildProperty(propertyName: String): String? {
    val properties = Properties()
    runCatching {
        rootProject.file("build.properties").inputStream().use { properties.load(it) }
    }

    return properties[propertyName] as String?
}

fun Project.toggleComposeCompilerReports(
    enabled: Boolean = enableComposeCompilerReportsConfig,
    compilerOptions: KotlinCommonCompilerOptions,
) {
    if (enabled) {
        val composeMetricDirectory = "${layout.buildDirectory.get()}/outputs/compose-metrics"
        compilerOptions.freeCompilerArgs.addAll(
            listOf(
                "-P",
                "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=$composeMetricDirectory"
            )
        )
        compilerOptions.freeCompilerArgs.addAll(
            listOf(
                "-P",
                "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=$composeMetricDirectory"
            )
        )
    }
}