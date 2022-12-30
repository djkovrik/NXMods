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
                implementation(project(Deps.Module.domain))
                implementation(project(Deps.Module.settings))

                implementation(Deps.JetBrains.Serialization.core)
                implementation(Deps.Badoo.Reaktive.coroutinesInterop)
                implementation(Deps.JetBrains.Ktor.clientCore)
                implementation(Deps.JetBrains.Ktor.clientNegotiation)
                implementation(Deps.JetBrains.Ktor.serialization)
            }
        }

        commonTest {
            dependencies {
                implementation(Deps.JetBrains.Serialization.core)
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
