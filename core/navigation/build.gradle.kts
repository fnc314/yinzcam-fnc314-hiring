plugins {
    alias(libs.plugins.com.yinzcam.fnc314.hiring.build.conventions.android.library.conventions)
}

dependencies {
    api(libs.bundles.androidx.compose.ui.material3.adaptive)
    implementation(projects.core.data)
    implementation(libs.androidx.navigation.compose)
}