plugins {
    id("multiplatform-setup")
    id("android-setup")
    id("com.squareup.sqldelight")
}

sqldelight {
    database("NexusDatabase") {
        packageName = "com.sedsoftware.nxmods.database"
    }
}

android {
    namespace = "com.sedsoftware.nxmods.database"
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(Deps.Module.domain))

                implementation(Deps.JetBrains.Serialization.core)
            }
        }

        androidMain {
            dependencies {
                implementation(Deps.Squareup.SQLDelight.androidDriver)
            }
        }

        iosMain {
            dependencies {
                implementation(Deps.Squareup.SQLDelight.nativeDriver)
            }
        }

        desktopMain {
            dependencies {
                implementation(Deps.Squareup.SQLDelight.sqliteDriver)
            }
        }

        commonTest {
            dependencies {
                implementation(project(Deps.Module.network))
            }
        }
    }
}
