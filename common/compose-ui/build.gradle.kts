plugins {
    id("multiplatform-setup-compose")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(Deps.Module.root))
                implementation(project(Deps.Module.Component.auth))

                implementation(Deps.ArkIvanov.Decompose.decompose)
                implementation(Deps.ArkIvanov.Decompose.extensionsJetbrains)
            }
        }
    }
}
