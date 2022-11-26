plugins {
    id("multiplatform-setup")
    id("android-setup")
}

android {
    namespace = "com.sedsoftware.nxmods.network"
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":common:domain"))

                implementation(Deps.JetBrains.DateTime.dateTime)
                implementation(Deps.JetBrains.Serialization.core)
                implementation(Deps.Badoo.Reaktive.reaktive)
            }
        }

        androidMain {
            dependencies {

            }
        }

        iosMain {
            dependencies {

            }
        }
    }
}
