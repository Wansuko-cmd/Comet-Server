plugins {
    kotlin("jvm") version libs.versions.kotlin apply false
    alias(projects.plugins.ktlint)
}

buildscript {
    dependencies {
        classpath(libs.gradle.kotlin)
        classpath(libs.gradle.ksp)
    }
}
