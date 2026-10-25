package com.yinzcam.fnc314.hiring.buildlogic.conventionplugins

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal val Project.versionCatalog: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

fun Project.composeUIBom() {
    versionCatalog.run {
        findLibrary("androidx.compose.bom").ifPresent {
            dependencies.platform(it).also { platform ->
                dependencies.addProvider(
                    "implementation",
                    platform,
                )
            }
        }
        findBundle("compose.bom").ifPresent {
            dependencies.addProvider(
                "implementation",
                it
            )
        }
    }
}