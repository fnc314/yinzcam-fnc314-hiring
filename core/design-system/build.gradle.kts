plugins {
    alias(libs.plugins.com.yinzcam.fnc314.hiring.build.conventions.android.library.conventions)
}

dependencies {
    val composeBom = platform(libs.androidx.compose.bom)
    api(composeBom)
    androidTestImplementation(composeBom)
    // Compose
    api(libs.androidx.compose.ui)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.bundles.androidx.compose.ui.material3.adaptive)
    api(libs.androidx.compose.foundation.layout)
    // Tooling
    debugApi(libs.androidx.compose.ui.tooling)
    // Instrumented tests
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}