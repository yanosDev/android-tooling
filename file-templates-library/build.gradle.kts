import de.yanosdev.buildsrc.dependency.YDVersion

plugins {
    id("android-tooling-base")
}

publishingConfig {
    artifactId = "file-templates"
    version = YDVersion.FileTemplatesVersion
}