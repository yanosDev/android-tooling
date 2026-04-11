package de.yanosdev.filetemplates.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

/**
 * Copies the bundled File Templates into the project's .idea/fileTemplates folder
 * so Android Studio picks them up automatically in New → File from Template.
 *
 * Usage:
 *   plugins { id("de.yanosdev.file-templates") version "1.0.0" }
 */
class FileTemplatesPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val task = target.tasks.register("installFileTemplates") {
            group = "YanosDev"
            description = "Copies YanosDev file templates into .idea/fileTemplates"

            doLast {
                val destDir = File(target.rootDir, ".idea/fileTemplates/internal")
                destDir.mkdirs()

                // Unpack templates bundled in the JAR
                val loader = FileTemplatesPlugin::class.java.classLoader
                val templates = listOf(
                    "YanosDev Composable Screen.kt",
                    "YanosDev ViewModel.kt",
                    "YanosDev UseCase.kt",
                    "YanosDev Repository.kt",
                )
                templates.forEach { name ->
                    val resource = loader.getResourceAsStream("fileTemplates/internal/$name")
                    if (resource != null) {
                        File(destDir, name).writeBytes(resource.readBytes())
                        target.logger.lifecycle("[YanosDev] Installed template: $name")
                    }
                }
            }
        }

        // Auto-run on sync
        target.afterEvaluate {
            target.tasks.findByName("preBuild")?.dependsOn(task)
        }
    }
}
