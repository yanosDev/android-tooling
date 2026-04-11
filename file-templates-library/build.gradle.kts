import de.yanosdev.buildsrc.dependency.YDVersion

plugins {
    id("yd-kotlin")
}

publishingConfig {
    artifactId = "file-templates-library"
    version = YDVersion.FileTemplatesVersion
}

val task = tasks.register("installFileTemplates") {
    group = "YD"
    description = "Copies YD file templates into .idea/fileTemplates"

    doLast {
        val destDir = rootProject.file(".idea/fileTemplates/internal/yd")
        destDir.mkdirs()

        val templatesDir = projectDir.resolve("src/main/resources/fileTemplates/internal")
        templatesDir.listFiles()?.forEach { template ->
            template.copyTo(destDir.resolve(template.name), overwrite = true)
            logger.lifecycle("[YD] Installed template: ${template.name}")
        }
    }
}



afterEvaluate {
    tasks.findByName("preBuild")?.dependsOn(task)
}
