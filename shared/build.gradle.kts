plugins {
    id("com.android.library") // TODO new syntax for dis?
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.cocoapods)
    alias(libs.plugins.kotlinx.parcelize)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.libres)
    alias(libs.plugins.sqlDelight)
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

                implementation(libs.jb.kotlin.datetime)
                implementation(libs.jb.kotlin.serialization)
                implementation(libs.jb.ktor.core)
                implementation(libs.jb.ktor.serialization)
                implementation(libs.jb.ktor.client.negotiation)
                implementation(libs.jb.markdown)

                implementation(libs.lib.reaktive.core)
                implementation(libs.lib.reaktive.interop)
                implementation(libs.lib.reaktive.utils)

                implementation(libs.ark.mvikotlin.core)
                implementation(libs.ark.mvikotlin.main)
                implementation(libs.ark.mvikotlin.rx)
                implementation(libs.ark.mvikotlin.extensions.reaktive)
                implementation(libs.ark.decompose.core)
                implementation(libs.ark.decompose.extensions)
                implementation(libs.ark.essenty)

                implementation(libs.lib.imageloader.core)
                implementation(libs.lib.libres.compose)
                implementation(libs.lib.settings.core)
                implementation(libs.lib.settings.test)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.android.appcompat)
                implementation(libs.android.preferences)
                implementation(libs.android.ktx)
                implementation(libs.jb.ktor.client.android)
                implementation(libs.lib.sqldelight.driver.android)
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
                implementation(libs.jb.ktor.client.ios)
                implementation(libs.lib.sqldelight.driver.native)
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
                implementation(compose.preview)

                implementation(libs.jb.ktor.client.desktop)
                implementation(libs.lib.sqldelight.driver.sqlite)
                implementation(libs.desktop.logback)
                implementation(libs.desktop.log4jcore)
                implementation(libs.desktop.log4jimpl)
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
