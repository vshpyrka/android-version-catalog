plugins {
    `version-catalog`
    `maven-publish`
}

repositories {
    mavenLocal()
    mavenCentral()
    google()
    gradlePluginPortal()
}

subprojects {
    apply(plugin = "version-catalog")
    apply(plugin = "maven-publish")

    val projectName = name
    val rootDir = rootProject.rootDir.path.toString()

    catalog {
        versionCatalog {
            from(files("$rootDir/gradle/catalogs/$projectName.versions.toml"))
        }
    }

    publishing {
        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/vshpyrka/android-version-catalog")
                credentials {
                    username = System.getenv("GITHUB_ACTOR")
                    password = System.getenv("GITHUB_PUBLISH_TOKEN")
                }
            }
        }
        afterEvaluate {
            publications {
                create<MavenPublication>("maven") {
                    pom {
                        name.set("android-version-catalog")
                        description.set("Android Version Catalog Example")
                        url.set("https://github.com/vshpyrka/android-version-catalog")
                        licenses {
                            // licensing info
                            license {
                                // license name
                                name.set("The Apache License, Version 2.0")
                                // license url
                                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                            }
                        }
                        developers {
                            // developer meta data
                            developer {
                                id.set("vshpyrka")
                                name.set("Victor Shpyrka")
                                email.set("victor.shpyrka@gmail.com")
                            }
                        }
                    }

                    group = "com.vshpyrka.android"
                    artifactId = "version-catalog-${project.name}"
                    version = System.getenv("PACKAGE_VERSION")
                    from(components["versionCatalog"])
                }
            }
        }
    }
}
