dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("./../gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "fnc314-yinzcam-hiring-build-logic"
include(":convention-plugins")