import com.yinzcam.fnc314.hiring.buildlogic.conventionplugins.composeUIBom

plugins {
    alias(libs.plugins.com.yinzcam.fnc314.hiring.build.conventions.android.library.conventions)
}
composeUIBom()
dependencies {
    api(
        projects.core.designSystem
    )

    implementation(
        projects.core.data
    )

    implementation(
        libs.bundles.coil
    )

    implementation(
        libs.bundles.compose.bom
    )
}