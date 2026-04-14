package de.yanosdev.livetemplates.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

/**
 * Mac only for now.
 */
class LiveTemplatesPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val logger = target.logger

        val task = target.tasks.register("installLiveTemplates") {
            group = "YD"
            description = "Copies YD live templates into Android Studio system directory"

            doLast {
                val userHome = System.getProperty("user.home")
                val googleSupportDir = File("$userHome/Library/Application Support/Google")

                val studioDir = googleSupportDir.listFiles()
                    ?.filter { it.isDirectory && it.name.startsWith("AndroidStudio") }
                    ?.maxByOrNull { it.name }

                if (studioDir == null) {
                    logger.error("❌ Android Studio configuration directory not found in $googleSupportDir")
                    return@doLast
                }

                val destDir = File(studioDir, "templates")
                if (!destDir.exists()) destDir.mkdirs()

                val resource = LiveTemplatesPlugin::class.java.classLoader
                    .getResourceAsStream("liveTemplates/YD.xml")

                logger.lifecycle("╔══════════════════════════════════════╗")
                logger.lifecycle("║      YD Global Template Installer    ║")
                logger.lifecycle("╚══════════════════════════════════════╝")
                logger.lifecycle("📁 Target Studio: ${studioDir.name}")
                logger.lifecycle("📁 Path: ${destDir.absolutePath}")

                if (resource != null) {
                    File(destDir, "YD.xml").writeBytes(resource.readBytes())
                    logger.lifecycle("  ✅ Installed: YD.xml")
                    logger.lifecycle("  💡 Note: Restart Android Studio to see changes.")
                } else {
                    logger.error("  ❌ Resource 'liveTemplates/YD.xml' not found in JAR!")
                }
                logger.lifecycle("══════════════════════════════════════")
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
