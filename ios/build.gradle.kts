plugins {
    id("multiplatform-setup")
    id("android-setup")
    id("org.jetbrains.kotlin.native.cocoapods")
}

kotlin {
    cocoapods {
        version = "1.0.0"
        summary = "NxMods Shared Module"
        homepage = "https://github.com/djkovrik/NXMods"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "ios"
            isStatic = true
        }
        extraSpecAttributes["resources"] = "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
    }

    sourceSets {
        named("iosMain") {
            dependencies {
                implementation(project(Deps.Module.Component.root))
                implementation(project(Deps.Module.utils))
                implementation(project(Deps.Module.UI.compose))

                implementation(Deps.ArkIvanov.Decompose.decompose)
                implementation(Deps.ArkIvanov.Decompose.extensions)
                implementation(Deps.ArkIvanov.MVIKotlin.mvikotlin)
                implementation(Deps.ArkIvanov.MVIKotlin.mvikotlinMain)
                implementation(Deps.KMM.ImageLoader.main)
            }
        }
    }
}
