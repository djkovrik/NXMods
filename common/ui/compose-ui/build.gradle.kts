plugins {
    id("multiplatform-setup")
    id("android-setup")
    id("io.github.skeptick.libres")
}

android {
    namespace = "com.sedsoftware.nxmods.ui"
}

libres {
    generatedClassName = "MR"
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(Deps.Module.domain))
                implementation(project(Deps.Module.utils))
                implementation(project(Deps.Module.UI.markdownWidget))

                implementation(project(Deps.Module.Component.root))
                implementation(project(Deps.Module.Component.auth))
                implementation(project(Deps.Module.Component.gameSelector))
                implementation(project(Deps.Module.Component.modList))
                implementation(project(Deps.Module.Component.home))
                implementation(project(Deps.Module.Component.preferences))
                implementation(project(Deps.Module.Component.modInfo))

                implementation(Deps.ArkIvanov.Decompose.decompose)
                implementation(Deps.ArkIvanov.Decompose.extensions)
                implementation(Deps.Badoo.Reaktive.reaktive)

                implementation(Deps.KMM.ImageLoader.main)
                implementation(Deps.KMM.LibRes.compose)
            }
        }

        androidMain {
            dependencies {

            }
        }

        desktopMain {
            dependencies {
                implementation(compose.preview)

                implementation(Deps.JetBrains.DateTime.dateTime)
            }
        }
    }
}
