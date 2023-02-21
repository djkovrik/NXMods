plugins {
    id("multiplatform-setup-compose")
    id("android-setup")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(Deps.Module.domain))

                implementation(project(Deps.Module.Component.root))
                implementation(project(Deps.Module.Component.auth))
                implementation(project(Deps.Module.Component.gameSelector))
                implementation(project(Deps.Module.Component.modList))
                implementation(project(Deps.Module.Component.home))

                implementation(Deps.ArkIvanov.Decompose.decompose)
                implementation(Deps.ArkIvanov.Decompose.extensions)
                implementation(Deps.Badoo.Reaktive.reaktive)

                implementation(Deps.KMM.ImageLoader.main)
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
