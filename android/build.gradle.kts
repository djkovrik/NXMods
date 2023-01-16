@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    kotlin("android")
    id("org.jetbrains.compose")
}

android {
    namespace = "com.sedsoftware.nxmods"

    compileSdk = 33

    defaultConfig {
        applicationId = "com.sedsoftware.nxmods"
        minSdk = 23
        targetSdk = 33
        versionCode = 100000
        versionName = "1.0.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    packagingOptions {
        resources.excludes.add("META-INF/*")
    }
}

dependencies {
    implementation(project(Deps.Module.Component.root))
    implementation(project(Deps.Module.domain))
    implementation(project(Deps.Module.database))
    implementation(project(Deps.Module.network))
    implementation(project(Deps.Module.settings))
    implementation(project(Deps.Module.ui))

    implementation(compose.material)

    implementation(Deps.AndroidX.AppCompat.appCompat)
    implementation(Deps.AndroidX.Activity.activityCompose)
    implementation(Deps.ArkIvanov.Decompose.decompose)
    implementation(Deps.ArkIvanov.Decompose.extensions)
    implementation(Deps.ArkIvanov.MVIKotlin.mvikotlin)
    implementation(Deps.ArkIvanov.MVIKotlin.mvikotlinMain)
    implementation(Deps.KMM.Settings.settings)
}
