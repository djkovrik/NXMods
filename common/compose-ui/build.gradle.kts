plugins {
    id("multiplatform-setup-compose")
    id("android-setup")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(Deps.Module.root))
                implementation(project(Deps.Module.Component.auth))

                implementation(Deps.ArkIvanov.Decompose.decompose)
                implementation(Deps.ArkIvanov.Decompose.extensions)
            }
        }

        desktopMain {
            dependencies {
                implementation(compose.preview)
            }
        }
    }
}
