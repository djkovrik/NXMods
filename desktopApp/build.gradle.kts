import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose)
}

kotlin {
    jvm()
    sourceSets {
        val jvmMain by getting  {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(project(":shared"))

                implementation(libs.jb.kotlin.coroutines.swing)
                implementation(libs.ark.decompose.core)
                implementation(libs.ark.decompose.extensions)
                implementation(libs.ark.mvikotlin.core)
                implementation(libs.ark.mvikotlin.main)
                implementation(libs.lib.reaktive.core)
                implementation(libs.lib.reaktive.interop)
                implementation(libs.lib.imageloader.core)
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
