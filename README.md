This repository contains several Gradle Version Catalog files which are published as a separate Git repository package, and can be included to a project separately.

* [libs.versions.toml](https://github.com/vshpyrka/android-version-catalog/blob/main/gradle/catalogs/libs.versions.toml) - Android dependencies list
* [plugins.versions.toml](https://github.com/vshpyrka/android-version-catalog/blob/main/gradle/catalogs/plugins.versions.toml) - Plugin dependencies list
* [sdk.versions.toml](https://github.com/vshpyrka/android-version-catalog/blob/main/gradle/catalogs/sdk.versions.toml) - Global parameters configuration, such as compile, min, target sdk versions
* [test.versions.toml](https://github.com/vshpyrka/android-version-catalog/blob/main/gradle/catalogs/test.versions.toml) - Android test dependencies list

More info about version catalogs: https://docs.gradle.org/current/userguide/platforms.html

According to the documentation of version catalog plugin:

<img width="600" alt="Screenshot 2024-01-10 at 5 40 37 PM" src="https://github.com/vshpyrka/android-version-catalog/assets/2741602/348e2e4b-683b-4cbc-9b77-7fe8119ed6d1">

Function [versionCatalog.from(...)](https://github.com/vshpyrka/android-version-catalog/blob/main/build.gradle.kts#L22) can be called only once with specified *.toml file. 
Consequently as a hot fix repository project contains empty module project for each *toml file, this makes possible to publish repository packages with separate *.toml files respectively.

Repository runs automatic job every day to verify new dependencies versions using [Renovate](https://docs.renovatebot.com/) tool.
Separate Github Action for renovate tool is located under workflows directory - [renovate.yml](https://github.com/vshpyrka/android-version-catalog/blob/main/.github/workflows/renovate.yml).
Renovate configuration is set in separate [renovate.json](https://github.com/vshpyrka/android-version-catalog/blob/main/renovate.json) file.
Renovate tool uses Gradle "repositories" builder to locate dependencies. For instance, androidx libraries can be located in google() repository.
```
repositories {
    mavenLocal()
    mavenCentral()
    google()
    gradlePluginPortal()
}
```

Additional [release.yml](https://github.com/vshpyrka/android-version-catalog/blob/main/.github/workflows/release.yml) workflow is used to react for merged PRs into main branch to create separate repository release and tag, and run separate command to publish separate repository package for each *.toml file.
