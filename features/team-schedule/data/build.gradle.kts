plugins {
    alias(libs.plugins.com.yinzcam.fnc314.hiring.build.conventions.android.library.conventions)
}

dependencies {
    api(
        projects.core.data
    )
    implementation(
        projects.core.network
    )
}