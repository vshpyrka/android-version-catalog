plugins {
    `version-catalog`
    `maven-publish`
}

repositories {
    mavenLocal()
    mavenCentral()
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
        afterEvaluate {
            publications {
                create<MavenPublication>("maven") {
                    pom {
                        name.set("VersionCatalog")
                        description.set("Android Version Catalog Example")
                        url.set("https://www.google.com")
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
                    version = "0.0.1"
                    from(components["versionCatalog"])
                }
            }
        }
    }
}
