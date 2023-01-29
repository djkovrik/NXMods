import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.konan.target.Family

plugins {
    id("multiplatform-setup")
    id("android-setup")
    id("kotlin-parcelize")
}

kotlin {
    targets
        .filterIsInstance<KotlinNativeTarget>()
        .filter { it.konanTarget.family == Family.IOS }
        .forEach { target ->
            target.binaries {
                framework {
                    baseName = "shared"
                    linkerOpts.add("-lsqlite3")
                    export(project(Deps.Module.database))
                    export(project(Deps.Module.network))
                    export(project(Deps.Module.settings))
                    export(project(Deps.Module.utils))
                    export(project(Deps.Module.domain))
                    export(project(Deps.Module.Component.auth))
                    export(project(Deps.Module.Component.gameSelector))
                    export(project(Deps.Module.Component.modList))
                    export(project(Deps.Module.Component.home))
                    export(Deps.ArkIvanov.Decompose.decompose)
                    export(Deps.ArkIvanov.MVIKotlin.mvikotlinMain)
                    export(Deps.ArkIvanov.Essenty.lifecycle)
                }
            }
        }

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
                implementation(Deps.ArkIvanov.MVIKotlin.mvikotlin)
                implementation(Deps.ArkIvanov.Decompose.decompose)
            }
        }
    }

    sourceSets {
        named("iosMain") {
            dependencies {
                api(project(Deps.Module.database))
                api(project(Deps.Module.network))
                api(project(Deps.Module.settings))
                api(project(Deps.Module.utils))
                api(project(Deps.Module.domain))
                api(project(Deps.Module.Component.auth))
                api(project(Deps.Module.Component.gameSelector))
                api(project(Deps.Module.Component.modList))
                api(project(Deps.Module.Component.home))
                api(Deps.ArkIvanov.Decompose.decompose)
                api(Deps.ArkIvanov.MVIKotlin.mvikotlinMain)
                api(Deps.ArkIvanov.Essenty.lifecycle)
            }
        }
    }
}

android {
    namespace = "com.sedsoftware.nxmods.root"
}
