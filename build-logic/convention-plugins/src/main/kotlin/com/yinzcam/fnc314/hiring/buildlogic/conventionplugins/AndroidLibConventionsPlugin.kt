package com.yinzcam.fnc314.hiring.buildlogic.conventionplugins

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.jvm.toolchain.JavaLanguageVersion

class AndroidLibConventionsPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.group =
            "com.yinzcam.fnc314.hiring.${target.parent?.name}"

        with(target.versionCatalog) {
            findPlugin("android.library").ifPresent {
                target.pluginManager.apply(it.get().pluginId)
            }
            findPlugin("compose.compiler").ifPresent {
                target.pluginManager.apply(it.get().pluginId)
            }
            findPlugin("kotlinx.serialization").ifPresent {
                target.pluginManager.apply(it.get().pluginId)
            }
            findLibrary("kotlinx.serialization.json").ifPresent {
                target.dependencies.addProvider(
                    "implementation",
                    it
                )
            }
            findLibrary("androidx.compose.bom").ifPresent {
                target.dependencies.platform(it).also { platform ->
                    target.dependencies.addProvider(
                        "implementation",
                        platform,
                    )
                }
            }
            findLibrary("androidx.compose.ui").ifPresent {
                target.dependencies.addProvider("implementation", it)
            }
            findBundle("androidx.lifecycle").ifPresent {
                target.dependencies.addProvider(
                    "implementation",
                    it
                )
            }
            findLibrary("desugaring").ifPresent {
                target.dependencies.addProvider(
                    "coreLibraryDesugaring",
                    it
                )
            }
        }

        target.extensions.configure(
            LibraryExtension::class.java
        ) {
            compileSdk = 37
            namespace = "com.yinzcam.fnc314.hiring.${target.parent?.name}.${target.name}".replace("-", "")
            defaultConfig {
                minSdk = 21
            }
            buildFeatures {
                compose = true
            }
            compileOptions {
                isCoreLibraryDesugaringEnabled = true
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }
            packaging {
                resources {
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                }
            }
        }
    }
}