plugins {
    `kotlin-dsl`
    `maven-publish`
}

val artifactId = "live-templates"

group = "de.yanosdev"
version = libs.versions.yd.live.templates.get()
