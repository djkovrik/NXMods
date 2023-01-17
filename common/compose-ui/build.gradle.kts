plugins {
    id("multiplatform-setup-compose")
    id("android-setup")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(Deps.Module.Component.root))
                implementation(project(Deps.Module.Component.auth))

                implementation(Deps.ArkIvanov.Decompose.decompose)
                implementation(Deps.ArkIvanov.Decompose.extensions)
                implementation(Deps.Badoo.Reaktive.reaktive)
            }
        }

        desktopMain {
            dependencies {
                implementation(compose.preview)
            }
        }
    }
}
