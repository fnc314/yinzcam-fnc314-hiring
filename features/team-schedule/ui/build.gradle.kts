plugins {
    alias(libs.plugins.com.yinzcam.fnc314.hiring.build.conventions.android.library.conventions)
}

dependencies {
    implementation(
        projects.features.teamSchedule.domain
    )
    implementation(
        projects.core.data
    )
    implementation(
        libs.bundles.coil
    )
    implementation(
        projects.core.designSystem
    )
    implementation(
        projects.core.widgets
    )
}