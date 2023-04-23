plugins {
    id("multiplatform-setup")
    id("android-setup")
}

android {
    namespace = "com.sedsoftware.nxmods.markdown"
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(Deps.JetBrains.markdown)
                implementation(Deps.KMM.ImageLoader.main)
            }
        }

        androidMain {
            dependencies {

            }
        }

        desktopMain {
            dependencies {
                implementation(compose.preview)
            }
        }
    }
}
