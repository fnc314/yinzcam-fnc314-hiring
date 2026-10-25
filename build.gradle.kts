// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
  alias(libs.plugins.com.yinzcam.fnc314.hiring.build.conventions.android.library.conventions) apply(false)
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.android.library) apply false
  alias(libs.plugins.compose.compiler) apply false
  alias(libs.plugins.kotlinx.serialization) apply false
  alias(libs.plugins.kotlin.jvm)
}

kotlin {
  jvmToolchain {
    languageVersion = libs.versions.jvmToolchain.map {
      JavaLanguageVersion.of(it)
    }
  }
}