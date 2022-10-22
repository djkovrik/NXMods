@file:Suppress("MemberNameEqualsClassName")

object Deps {
    object JetBrains {
        object Kotlin {
            private val VERSION get() = "1.7.20"
            val gradlePlugin get() = "org.jetbrains.kotlin:kotlin-gradle-plugin:$VERSION"
            val testCommon get() = "org.jetbrains.kotlin:kotlin-test-common:$VERSION"
            val testJunit get() = "org.jetbrains.kotlin:kotlin-test-junit:$VERSION"
            val testAnnotationsCommon get() = "org.jetbrains.kotlin:kotlin-test-annotations-common:$VERSION"
        }

        object Compose {
             private val VERSION get() = "1.2.0"
             val gradlePlugin get() = "org.jetbrains.compose:compose-gradle-plugin:$VERSION"
        }

        object Ktor {
            private val VERSION get() = "2.1.2"
            val clientCore = "io.ktor:ktor-client-core:$VERSION"
            val engineAndroid = "io.ktor:ktor-client-okhttp:$VERSION"
            val engineIos = "io.ktor:ktor-client-darwin:$VERSION"
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
            private const val VERSION = "1.0.0-alpha-06"
            const val decompose = "com.arkivanov.decompose:decompose:$VERSION"
            const val extensionsJetbrains = "com.arkivanov.decompose:extensions-compose-jetbrains:$VERSION"
            const val extensionsJetpack = "com.arkivanov.decompose:extensions-compose-jetpack:$VERSION"
        }

        object Essenty {
            private const val VERSION = "0.6.0"
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

    }
}
