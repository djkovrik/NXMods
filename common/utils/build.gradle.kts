plugins {
    id("multiplatform-setup")
    id("android-setup")
}
android {
    namespace = "com.sedsoftware.nxmods.utils"
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(Deps.ArkIvanov.MVIKotlin.rx)
                implementation(Deps.ArkIvanov.MVIKotlin.mvikotlin)
                implementation(Deps.ArkIvanov.MVIKotlin.mvikotlinExtensionsReaktive)
                implementation(Deps.ArkIvanov.Decompose.decompose)

                implementation(Deps.KMM.ImageLoader.main)
            }
        }
    }
}
