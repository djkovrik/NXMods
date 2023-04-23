plugins {
    id("kotlin-multiplatform")
    id("kotlinx-serialization")
    id("com.android.library")
    id("org.jetbrains.compose")
}

kotlin {
    android()
    jvm("desktop")
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Deps.Badoo.Reaktive.reaktive)
                implementation(Deps.JetBrains.DateTime.dateTime)

                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation(compose.materialIconsExtended)

                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(Deps.Badoo.Reaktive.reaktive)
                implementation(Deps.Badoo.Reaktive.reaktiveTesting)
                implementation(Deps.JetBrains.DateTime.dateTime)
                implementation(Deps.JetBrains.Kotlin.testCommon)
                implementation(Deps.JetBrains.Kotlin.testAnnotationsCommon)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Deps.AndroidX.AppCompat.appCompat)
                implementation(Deps.AndroidX.Ktx.ktx)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(Deps.JetBrains.Kotlin.testJunit)
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
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
            }
        }

        val desktopTest by getting {
            dependencies {
                implementation(Deps.JetBrains.Kotlin.testJunit)
            }
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
}
