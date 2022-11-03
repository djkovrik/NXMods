plugins {
    id("multiplatform-setup")
    id("android-setup")
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
