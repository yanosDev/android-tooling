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

                val loader = FileTemplatesPlugin::class.java.classLoader
                provide(loader = loader, target = target) {
                    install(destDir = ".idea/", fileName = "base.txt")
                    install(destDir = ".idea/fileTemplates/", fileName = "lint.txt")
                    install(destDir = ".idea/fileTemplates/includes", fileName = "includes.txt")
                }
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

    private fun provide(
        loader: ClassLoader,
        target: Project,
        block: InstallScope.() -> Unit
    ) =
        InstallScopeImpl(
            loader = loader,
            target = target,
        ).block()

    private fun InstallScope.install(destDir: String, fileName: String) {
        val destDir = File(target.rootDir, destDir)
        target.logger.lifecycle("📁 Destination: ${destDir.absolutePath}")
        destDir.mkdirs()

        val indexStream = loader.getResourceAsStream("fileTemplates/internal/$fileName")
        if (indexStream == null) {
            target.logger.error("❌ $fileName not found in JAR resources!")
            target.logger.error("   Make sure $fileName exists at: src/main/resources/fileTemplates/internal/$fileName")
            return
        }
        target.logger.lifecycle("✅ Found $fileName")

        val templates = indexStream.bufferedReader().readLines()
            .map { it.trim() }
            .filter { it.isNotEmpty() && !it.startsWith("//") }

        target.logger.lifecycle("📋 Found ${templates.size} template(s) in lint.txt")
        target.logger.lifecycle("──────────────────────────────────────")

        var successCount = 0
        var failCount = 0

        templates.forEach { name ->
            val resource = loader.getResourceAsStream("fileTemplates/internal/$name")
            val nameWithoutFTExtension = name.split("/").last().removeSuffix(".ft")
            if (resource != null) {
                val file = File(destDir, nameWithoutFTExtension)
                file.writeBytes(resource.readBytes())
                target.logger.lifecycle("  ✅ Installed: $nameWithoutFTExtension")
                successCount++
            } else {
                target.logger.error("  ❌ Not found in JAR: $nameWithoutFTExtension")
                failCount++
            }
        }

        target.logger.lifecycle("──────────────────────────────────────")
        target.logger.lifecycle("✅ Installed: $successCount  ❌ Failed: $failCount")
        target.logger.lifecycle("══════════════════════════════════════")

        successCount to failCount
    }
}

interface InstallScope {
    val loader: ClassLoader
    val target: Project
}

data class InstallScopeImpl(
    override val loader: ClassLoader,
    override val target: Project,
) : InstallScope