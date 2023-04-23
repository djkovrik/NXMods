plugins {
    id("multiplatform-setup")
    id("android-setup")
    id("kotlin-parcelize")
}

kotlin {

    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(project(Deps.Module.database))
                implementation(project(Deps.Module.network))
                implementation(project(Deps.Module.settings))
                implementation(project(Deps.Module.utils))
                implementation(project(Deps.Module.domain))
                implementation(project(Deps.Module.Component.auth))
                implementation(project(Deps.Module.Component.gameSelector))
                implementation(project(Deps.Module.Component.modList))
                implementation(project(Deps.Module.Component.home))
                implementation(project(Deps.Module.Component.preferences))
                implementation(project(Deps.Module.Component.modInfo))
                implementation(Deps.ArkIvanov.MVIKotlin.mvikotlin)
                implementation(Deps.ArkIvanov.MVIKotlin.mvikotlinMain)
                implementation(Deps.ArkIvanov.Decompose.decompose)
                implementation(Deps.KMM.Settings.settings)
            }
        }
    }
}

android {
    namespace = "com.sedsoftware.nxmods.root"
}
