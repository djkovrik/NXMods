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
        minSdk = 24
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
    implementation(project(Deps.Module.utils))
    implementation(project(Deps.Module.UI.compose))

    implementation(compose.material)

    implementation(Deps.AndroidX.AppCompat.appCompat)
    implementation(Deps.AndroidX.Activity.activityCompose)
    implementation(Deps.ArkIvanov.Decompose.decompose)
    implementation(Deps.ArkIvanov.Decompose.extensions)
    implementation(Deps.ArkIvanov.MVIKotlin.mvikotlin)
    implementation(Deps.KMM.ImageLoader.main)
}
