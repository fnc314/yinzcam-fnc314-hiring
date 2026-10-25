pluginManagement {
    includeBuild("build-logic")
    plugins {
        id("org.jetbrains.kotlin.jvm") version("2.4.21")
        id("com.android.settings") version("9.4.1")
        id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
        id("dev.fnc314.gradle.plugins.settings.project-collections-gradle-settings-plugin") version "5.0.3"
    }
}

plugins {
    id("dev.fnc314.gradle.plugins.settings.project-collections-gradle-settings-plugin")
    id("org.gradle.toolchains.foojay-resolver-convention")
    id("com.android.settings")
    `jvm-toolchain-management`
}

rootProject.name = "fnc314-hiring"

android {
    buildToolsVersion = "37.0.0"
    targetSdk {
        version = release(37)
    }
    compileSdk {
        version = release(37)
    }
    minSdk {
        version = release(21)
    }
    lint {
        absolutePaths = true
        checkDependencies = true
        checkGeneratedSources = true
        explainIssues = true
        noLines = false
        printTextReport = true
        showAll = true
    }
}

projectCollections {
    registerProjectCollection("core")
    "features" toDepthOf 3
}

include(":app")
