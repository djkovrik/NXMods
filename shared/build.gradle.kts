plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("com.squareup.sqldelight")
    id("kotlinx-serialization")
    id("kotlin-parcelize")
    id("io.github.skeptick.libres")
}

sqldelight {
    database("NexusDatabase") {
        packageName = "com.sedsoftware.nxmods.database"
    }
}

libres {
    generatedClassName = "MainRes"
}

kotlin {
    android()

    jvm("desktop")

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        version = "1.0.0"
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
        extraSpecAttributes["resources"] = "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation(compose.materialIconsExtended)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                implementation(Deps.JetBrains.DateTime.dateTime)
                implementation(Deps.JetBrains.Serialization.core)
                implementation(Deps.JetBrains.Ktor.clientCore)
                implementation(Deps.JetBrains.Ktor.clientNegotiation)
                implementation(Deps.JetBrains.Ktor.serialization)
                implementation(Deps.JetBrains.markdown)

                implementation(Deps.Badoo.Reaktive.reaktive)
                implementation(Deps.Badoo.Reaktive.utils)
                implementation(Deps.Badoo.Reaktive.coroutinesInterop)

                implementation(Deps.ArkIvanov.MVIKotlin.rx)
                implementation(Deps.ArkIvanov.MVIKotlin.mvikotlin)
                implementation(Deps.ArkIvanov.MVIKotlin.mvikotlinMain)
                implementation(Deps.ArkIvanov.MVIKotlin.mvikotlinExtensionsReaktive)
                implementation(Deps.ArkIvanov.Decompose.decompose)
                implementation(Deps.ArkIvanov.Decompose.extensions)
                implementation(Deps.ArkIvanov.Essenty.lifecycle)

                implementation(Deps.KMM.ImageLoader.main)
                implementation(Deps.KMM.LibRes.compose)
                implementation(Deps.KMM.Settings.settings)
                implementation(Deps.KMM.Settings.settingsTest)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Deps.AndroidX.AppCompat.appCompat)
                implementation(Deps.AndroidX.Preferences.preferences) {
                    exclude("org.jetbrains.kotlinx", "kotlinx-coroutines-core")
                }
                implementation(Deps.AndroidX.Ktx.ktx)
                implementation(Deps.JetBrains.Ktor.engineAndroid)
                implementation(Deps.Squareup.SQLDelight.androidDriver)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                implementation(Deps.JetBrains.Ktor.engineIos)
                implementation(Deps.Squareup.SQLDelight.nativeDriver)
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
                implementation(compose.preview)

                implementation(Deps.JetBrains.Ktor.engineDesktop)
                implementation(Deps.Desktop.log4jcore)
                implementation(Deps.Desktop.log4jimpl)
                implementation(Deps.Squareup.SQLDelight.sqliteDriver)
            }
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "com.sedsoftware.nxmods"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res", "src/commonMain/resources", "build/generated/libres/android/resources")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
        targetSdk = (findProperty("android.targetSdk") as String).toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        jvmToolchain(11)
    }
}
