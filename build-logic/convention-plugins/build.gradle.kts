import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

kotlin {
    jvmToolchain {
        languageVersion = libs.versions.jvmToolchain.map {
            JavaLanguageVersion.of(it)
        }
    }
    compilerOptions {
        apiVersion = libs.versions.kotlin.map {
            KotlinVersion.fromVersion(it)
        }
        languageVersion = libs.versions.kotlin.map {
            KotlinVersion.fromVersion(it)
        }
    }
}

gradlePlugin {
    plugins {
        create("android-library-conventions") {
            implementationClass = "com.yinzcam.fnc314.hiring.buildlogic.conventionplugins.AndroidLibConventionsPlugin"
            id = libs.plugins.com.yinzcam.fnc314.hiring.build.conventions.android.library.conventions.get().pluginId
            description = "Conventions for `com.android.library`"
            displayName = "Android Library Plugin Conventions"
        }
    }
}

dependencies {
    implementation(
        libs.android.gradle.plugin.api
    )
    gradleApi()
    implementation(
        libs.kotlin.gradle.plugin
    )
}