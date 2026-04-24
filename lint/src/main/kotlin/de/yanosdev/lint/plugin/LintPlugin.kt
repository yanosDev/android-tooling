package de.yanosdev.lint.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

/**
 * Gradle plugin that wires in the custom lint rules library.
 *
 * Usage:
 *   plugins { id("de.yanosdev.lint") version "1.0.0" }
 */
class LintPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.pluginManager.withPlugin("com.android.base") {
            // Only runs if the project has an Android plugin applied
            target.dependencies.add(
                "lintChecks",
                target.extensions.getByType<VersionCatalogsExtension>()
                    .named("libs")
                    .findLibrary("yd-lint")
                    .get()
            )
            target.logger.lifecycle("[YanosDev] Lint rules applied to ${target.name}")
        }
    }
}