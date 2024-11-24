@file:Suppress("UnstableApiUsage")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("projects") {
            from(files("./gradle/projects.versions.toml"))
        }
    }
}

rootProject.name = "Comet"

include(":lib")
include(":domain")
include(":usecase")
include(":utils")
