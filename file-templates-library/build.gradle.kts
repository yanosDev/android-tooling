import de.yanosdev.buildsrc.dependency.YDVersion

plugins {
    id("yd-kotlin")
}

publishingConfig {
    artifactId = "file-templates"
    version = YDVersion.FileTemplatesVersion
}