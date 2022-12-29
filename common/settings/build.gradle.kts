plugins {
    id("multiplatform-setup")
    id("android-setup")
}

android {
    namespace = "com.sedsoftware.nxmods.settings"
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(Deps.Module.domain))

                implementation(Deps.KMM.Settings.settings)
                implementation(Deps.KMM.Settings.settingsTest)
            }
        }

        androidMain {
            dependencies {
                implementation(Deps.AndroidX.Preferences.preferences)
            }
        }

        iosMain {
            dependencies {

            }
        }
    }
}
