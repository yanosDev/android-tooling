import de.yanosdev.buildsrc.dependency.YDVersion

plugins {
    id("android-tooling-base")
}

publishingConfig {
    artifactId = "live-templates"
    version = YDVersion.LiveTemplatesVersion
}