plugins {
    `kotlin-dsl`
    id("com.louiscad.complete-kotlin") version "1.1.0"
}

allprojects {
    repositories {
        mavenLocal()
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}
