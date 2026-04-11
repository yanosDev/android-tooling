package de.yanosdev.lint.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Gradle plugin that wires in the custom lint rules library.
 *
 * Usage:
 *   plugins { id("de.yanosdev.lint") version "1.0.0" }
 */
class LintPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.dependencies.add(
            "lintChecks",
            "de.yanosdev:lint:1.0.0"
        )
        target.logger.lifecycle("[YanosDev] Lint rules applied to ${target.name}")
    }
}
