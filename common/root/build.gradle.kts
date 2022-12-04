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
                    export(project(":common:database"))
                    export(project(":common:network"))
                    export(project(":common:settings"))
                    export(project(":common:domain"))
                    export(Deps.ArkIvanov.Decompose.decompose)
                    export(Deps.ArkIvanov.MVIKotlin.mvikotlinMain)
                    export(Deps.ArkIvanov.Essenty.lifecycle)
                }
            }
        }

    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(project(":common:database"))
                implementation(project(":common:network"))
                implementation(project(":common:settings"))
                implementation(project(":common:domain"))
                implementation(Deps.ArkIvanov.MVIKotlin.mvikotlin)
                implementation(Deps.ArkIvanov.Decompose.decompose)
                implementation(Deps.Badoo.Reaktive.reaktive)
            }
        }
    }

    sourceSets {
        named("iosMain") {
            dependencies {
                api(project(":common:database"))
                api(project(":common:network"))
                api(project(":common:settings"))
                api(project(":common:domain"))
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
