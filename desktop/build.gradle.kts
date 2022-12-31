import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    jvm {
        withJava()
    }

    sourceSets {
        named("jvmMain") {
            dependencies {
                implementation(compose.desktop.currentOs)

                implementation(project(Deps.Module.root))

                implementation(Deps.JetBrains.Coroutines.swing)
                implementation(Deps.ArkIvanov.Decompose.decompose)
                implementation(Deps.ArkIvanov.Decompose.extensionsJetbrains)
                implementation(Deps.ArkIvanov.MVIKotlin.mvikotlin)
                implementation(Deps.ArkIvanov.MVIKotlin.mvikotlinMain)
                implementation(Deps.Badoo.Reaktive.reaktive)
                implementation(Deps.Badoo.Reaktive.coroutinesInterop)
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "com.sedsoftware.nxmods.desktop.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "NxModsDesktop"
            packageVersion = "1.0.0"

            modules("java.sql")

            windows {
                menuGroup = "NxMods"
                upgradeUuid = "C3C0011B-3414-40D2-A9DE-AB0E0BE3F8F0"
            }
        }
    }
}
