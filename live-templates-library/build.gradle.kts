import de.yanosdev.buildsrc.dependency.YDVersion

plugins {
    id("yd-kotlin")
}

publishingConfig {
    artifactId = "live-templates-library"
    version = YDVersion.LiveTemplatesVersion
}