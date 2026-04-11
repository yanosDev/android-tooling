package de.yanosdev.buildsrc.util

import org.gradle.api.Project
import java.util.Properties
import kotlin.io.inputStream
import kotlin.use


val Project.allWarningsAsErrorsConfig: Boolean
    get() = getBuildProperty("allWarningsAsErrors")
        ?.toBooleanStrictOrNull() ?: true

fun Project.getBuildProperty(propertyName: String): String? {
    val properties = Properties()
    runCatching {
        rootProject.file("build.properties").inputStream().use { properties.load(it) }
    }

    return properties[propertyName] as String?
}