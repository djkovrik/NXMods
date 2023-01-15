@file:Suppress("MemberNameEqualsClassName")

object Deps {
    object JetBrains {
        object Kotlin {
            const val VERSION = "1.7.20"
            const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$VERSION"
            const val testCommon = "org.jetbrains.kotlin:kotlin-test-common:$VERSION"
            const val testJunit = "org.jetbrains.kotlin:kotlin-test-junit:$VERSION"
            const val testAnnotationsCommon = "org.jetbrains.kotlin:kotlin-test-annotations-common:$VERSION"
        }

        object Coroutines {
            private val VERSION get() = "1.6.4"
            val swing get() = "org.jetbrains.kotlinx:kotlinx-coroutines-swing:$VERSION"
        }

        object Compose {
            private const val VERSION = "1.2.0"
            const val gradlePlugin = "org.jetbrains.compose:compose-gradle-plugin:$VERSION"
        }

        object Ktor {
            private const val VERSION = "2.1.3"
            const val clientCore = "io.ktor:ktor-client-core:$VERSION"
            const val clientNegotiation = "io.ktor:ktor-client-content-negotiation:$VERSION"
            const val serialization = "io.ktor:ktor-serialization-kotlinx-json:$VERSION"
            const val engineAndroid = "io.ktor:ktor-client-okhttp:$VERSION"
            const val engineIos = "io.ktor:ktor-client-darwin:$VERSION"
            const val engineDesktop = "io.ktor:ktor-client-curl:$VERSION"
        }

        object DateTime {
            private const val VERSION = "0.4.0"
            const val dateTime = "org.jetbrains.kotlinx:kotlinx-datetime:$VERSION"
        }

        object Serialization {
            private const val VERSION = "1.4.1"
            const val gradlePlugin = "org.jetbrains.kotlin:kotlin-serialization:${JetBrains.Kotlin.VERSION}"
            const val core = "org.jetbrains.kotlinx:kotlinx-serialization-json:$VERSION"
        }
    }

    object Android {
        object Tools {
            object Build {
                const val gradlePlugin = "com.android.tools.build:gradle:7.3.0"
            }
        }
    }

    object AndroidX {
        object AppCompat {
            const val appCompat = "androidx.appcompat:appcompat:1.5.1"
        }

        object Ktx {
            const val ktx = "androidx.core:core-ktx:1.3.1"
        }

        object Activity {
            const val activityCompose = "androidx.activity:activity-compose:1.6.0"
        }

        object Preferences {
            const val preferences = "androidx.preference:preference-ktx:1.2.0"
        }
    }

    object ArkIvanov {
        object MVIKotlin {
            private const val VERSION = "3.0.2"
            const val rx = "com.arkivanov.mvikotlin:rx:$VERSION"
            const val mvikotlin = "com.arkivanov.mvikotlin:mvikotlin:$VERSION"
            const val mvikotlinMain = "com.arkivanov.mvikotlin:mvikotlin-main:$VERSION"
            const val mvikotlinLogging = "com.arkivanov.mvikotlin:mvikotlin-logging:$VERSION"
            const val mvikotlinTimeTravel = "com.arkivanov.mvikotlin:mvikotlin-timetravel:$VERSION"
            const val mvikotlinExtensionsReaktive = "com.arkivanov.mvikotlin:mvikotlin-extensions-reaktive:$VERSION"
        }

        object Decompose {
            private const val VERSION = "1.0.0-beta-02"
            const val decompose = "com.arkivanov.decompose:decompose:$VERSION"
            const val extensions = "com.arkivanov.decompose:extensions-compose-jetbrains:$VERSION"
        }

        object Essenty {
            private const val VERSION = "0.7.0"
            const val lifecycle = "com.arkivanov.essenty:lifecycle:$VERSION"
        }
    }

    object Badoo {
        object Reaktive {
            private const val VERSION = "1.2.2"
            const val reaktive = "com.badoo.reaktive:reaktive:$VERSION"
            const val reaktiveTesting = "com.badoo.reaktive:reaktive-testing:$VERSION"
            const val utils = "com.badoo.reaktive:utils:$VERSION"
            const val coroutinesInterop = "com.badoo.reaktive:coroutines-interop:$VERSION"
        }
    }

    object Squareup {
        object SQLDelight {
            private const val VERSION = "1.5.4"

            const val gradlePlugin = "com.squareup.sqldelight:gradle-plugin:$VERSION"
            const val androidDriver = "com.squareup.sqldelight:android-driver:$VERSION"
            const val sqliteDriver = "com.squareup.sqldelight:sqlite-driver:$VERSION"
            const val nativeDriver = "com.squareup.sqldelight:native-driver:$VERSION"
        }
    }

    object KMM {
        object Settings {
            private const val VERSION = "1.0.0-RC"
            const val settings = "com.russhwolf:multiplatform-settings:$VERSION"
            const val settingsTest = "com.russhwolf:multiplatform-settings-test:$VERSION"
        }
    }

    object Module {
        const val root = ":common:root"
        const val database = ":common:database"
        const val network = ":common:network"
        const val settings = ":common:settings"
        const val domain = ":common:domain"
        const val utils = ":common:utils"
        const val ui = ":common:compose-ui"

        object Component {
            const val auth = ":common:component:auth"
        }
    }
}
