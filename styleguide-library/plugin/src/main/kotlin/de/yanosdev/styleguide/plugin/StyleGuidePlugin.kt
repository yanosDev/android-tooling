package de.yanosdev.styleguide.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Gradle plugin that automatically adds the StyleGuide library as a dependency.
 *
 * Usage in consumer project:
 *   plugins { id("de.yanosdev.styleguide") version "1.0.0" }
 */
class StyleGuidePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.dependencies.add(
            "implementation",
            "de.yanosdev:styleguide:1.0.0"
        )
        target.logger.lifecycle("[YanosDev] StyleGuide applied to ${target.name}")
    }
}
