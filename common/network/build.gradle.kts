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
                implementation(Deps.Badoo.Reaktive.coroutinesInterop)
                implementation(Deps.JetBrains.Ktor.clientCore)
                implementation(Deps.JetBrains.Ktor.clientNegotiation)
                implementation(Deps.JetBrains.Ktor.serialization)
            }
        }

        androidMain {
            dependencies {
                implementation(Deps.JetBrains.Ktor.engineAndroid)
            }
        }

        iosMain {
            dependencies {
                implementation(Deps.JetBrains.Ktor.engineIos)
            }
        }
    }
}
