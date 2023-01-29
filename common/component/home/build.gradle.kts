plugins {
    id("multiplatform-setup")
    id("android-setup")
    id("kotlin-parcelize")
}

android {
    namespace = "com.sedsoftware.nxmods.component.home"
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(Deps.Module.domain))
                implementation(project(Deps.Module.utils))
                implementation(project(Deps.Module.Component.modList))

                implementation(Deps.ArkIvanov.MVIKotlin.rx)
                implementation(Deps.ArkIvanov.MVIKotlin.mvikotlin)
                implementation(Deps.ArkIvanov.MVIKotlin.mvikotlinExtensionsReaktive)
                implementation(Deps.ArkIvanov.Decompose.decompose)
            }
        }

        commonTest {
            dependencies {
                implementation(Deps.ArkIvanov.MVIKotlin.mvikotlinMain)
            }
        }
    }
}
