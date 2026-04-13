package de.yanosdev.livetemplates.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

class LiveTemplatesPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val rootDir = target.rootDir
        val logger = target.logger

        val task = target.tasks.register("installLiveTemplates") {
            group = "YD"
            description = "Copies YD live templates into .idea/templates"

            doLast {
                logger.lifecycle("╔══════════════════════════════════════╗")
                logger.lifecycle("║      YD Live Templates Installer     ║")
                logger.lifecycle("╚══════════════════════════════════════╝")

                val destDir = File(rootDir, ".idea/templates")
                logger.lifecycle("📁 Destination: ${destDir.absolutePath}")
                destDir.mkdirs()

                logger.lifecycle("──────────────────────────────────────")

                val resource = LiveTemplatesPlugin::class.java.classLoader
                    .getResourceAsStream("liveTemplates/YD.xml")

                if (resource != null) {
                    File(destDir, "YD.xml").writeBytes(resource.readBytes())
                    logger.lifecycle("  ✅ Installed: YD.xml")
                    logger.lifecycle("──────────────────────────────────────")
                    logger.lifecycle("✅ Installed: 1  ❌ Failed: 0")
                } else {
                    logger.error("  ❌ YD.xml not found in JAR resources!")
                    logger.error("   Make sure YD.xml exists at: src/main/resources/liveTemplates/YD.xml")
                    logger.lifecycle("──────────────────────────────────────")
                    logger.lifecycle("✅ Installed: 0  ❌ Failed: 1")
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