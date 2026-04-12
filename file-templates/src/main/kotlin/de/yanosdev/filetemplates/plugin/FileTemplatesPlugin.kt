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
            group = "YD"
            description = "Copies YD file templates into .idea/fileTemplates"

            doLast {
                target.logger.lifecycle("╔══════════════════════════════════════╗")
                target.logger.lifecycle("║     YD File Templates Installer      ║")
                target.logger.lifecycle("╚══════════════════════════════════════╝")

                val destDir = File(target.rootDir, ".idea/fileTemplates/")
                target.logger.lifecycle("📁 Destination: ${destDir.absolutePath}")
                destDir.mkdirs()

                val loader = FileTemplatesPlugin::class.java.classLoader

                // Read index file
                val indexStream = loader.getResourceAsStream("fileTemplates/internal/index.txt")
                if (indexStream == null) {
                    target.logger.error("❌ index.txt not found in JAR resources!")
                    target.logger.error("   Make sure index.txt exists at: src/main/resources/fileTemplates/internal/index.txt")
                    return@doLast
                }
                target.logger.lifecycle("✅ Found index.txt")

                val templates = indexStream.bufferedReader().readLines()
                    .map { it.trim() }
                    .filter { it.isNotEmpty() && !it.startsWith("//") }

                target.logger.lifecycle("📋 Found ${templates.size} template(s) in index.txt")
                target.logger.lifecycle("──────────────────────────────────────")

                var successCount = 0
                var failCount = 0

                templates.forEach { name ->
                    val resource = loader.getResourceAsStream("fileTemplates/internal/$name")
                    if (resource != null) {
                        val file = File(destDir, name)
                        file.writeBytes(resource.readBytes())
                        target.logger.lifecycle("  ✅ Installed: $name")
                        successCount++
                    } else {
                        target.logger.error("  ❌ Not found in JAR: $name")
                        failCount++
                    }
                }

                target.logger.lifecycle("──────────────────────────────────────")
                target.logger.lifecycle("✅ Installed: $successCount  ❌ Failed: $failCount")
                target.logger.lifecycle("══════════════════════════════════════")
            }
        }

        target.afterEvaluate {
            target.tasks.matching {
                it.name.startsWith("prepareKotlinBuildScriptModel")
            }.configureEach {
                dependsOn(task)
            }
        }
    }
}