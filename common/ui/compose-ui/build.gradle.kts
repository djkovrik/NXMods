plugins {
    id("multiplatform-setup-compose")
    id("android-setup")
    id("dev.icerock.mobile.multiplatform-resources")
}

android {
    namespace = "com.sedsoftware.nxmods.ui"
}

multiplatformResources {
    multiplatformResourcesPackage = "com.sedsoftware.nxmods.ui"
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

                api(Deps.KMM.Mokko.resources)
            }
        }

        androidMain {
            dependencies {
                api(Deps.KMM.Mokko.resourcesCompose)
            }
        }

        desktopMain {
            dependencies {
                implementation(compose.preview)

                implementation(Deps.JetBrains.DateTime.dateTime)

                api(Deps.KMM.Mokko.resourcesCompose)
            }
        }
    }
}