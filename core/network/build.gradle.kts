import com.yinzcam.fnc314.hiring.buildlogic.conventionplugins.composeUIBom

plugins {
  alias(libs.plugins.com.yinzcam.fnc314.hiring.build.conventions.android.library.conventions)
}

composeUIBom()

dependencies {
  api(
    libs.okhttp
  )
}