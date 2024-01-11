// Plugin management
pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        mavenCentral()
        google()
    }
}

rootProject.name = "android-version-catalogs"
include(":libs")
include(":plugins")
include(":sdk")
include(":test")
