pluginManagement {
    includeBuild("build-logic")
    plugins {
        id("dev.fnc314.gradle.plugins.settings.project-collections-gradle-settings-plugin") version "5.0.3"
    }
}

plugins {
    id("dev.fnc314.gradle.plugins.settings.project-collections-gradle-settings-plugin")
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "fnc314-hiring"

projectCollections {
    registerProjectCollection("core")
    "features" toDepthOf 3
}

include(":app")
