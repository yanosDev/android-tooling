package de.yanosdev.livetemplates.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

/**
 * Copies the YanosDev.xml live templates into the project-scoped
 * .idea/templates directory so Android Studio loads them without
 * any manual import step.
 *
 * Usage:
 *   plugins { id("de.yanosdev.live-templates") version "1.0.0" }
 */
class LiveTemplatesPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val task = target.tasks.register("installLiveTemplates") {
            group = "YanosDev"
            description = "Copies YanosDev live templates into .idea/templates"

            doLast {
                val destDir = File(target.rootDir, ".idea/templates")
                destDir.mkdirs()

                val resource = LiveTemplatesPlugin::class.java.classLoader
                    .getResourceAsStream("liveTemplates/YanosDev.xml")

                if (resource != null) {
                    File(destDir, "YanosDev.xml").writeBytes(resource.readBytes())
                    target.logger.lifecycle("[YanosDev] Live templates installed in ${destDir.path}")
                } else {
                    target.logger.warn("[YanosDev] Live template resource not found in JAR")
                }
            }
        }

        target.afterEvaluate {
            target.tasks.findByName("preBuild")?.dependsOn(task)
        }
    }
}
