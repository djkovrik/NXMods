plugins {
    id("multiplatform-setup")
    id("android-setup")
}
android {
    namespace = "com.sedsoftware.nxmods.domain"
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(Deps.JetBrains.DateTime.dateTime)
                implementation(Deps.Badoo.Reaktive.reaktive)
            }
        }
    }
}
