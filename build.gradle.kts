plugins {
    `kotlin-dsl`
    id("com.louiscad.complete-kotlin") version "1.1.0"
    id("io.gitlab.arturbosch.detekt") version "1.22.0-RC2"
}

allprojects {
    repositories {
        mavenLocal()
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

detekt {
    buildUponDefaultConfig = true
    allRules = false
    source = files("$projectDir/android/", "$projectDir/common/")
    config = files("$projectDir/detekt/base-config.yml")
    baseline = file("$projectDir/detekt/baseline.xml")
    parallel = true
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    reports {
        html.required.set(true)
        html.outputLocation.set(file("$projectDir/detekt/reports/detekt.html"))
    }
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    jvmTarget = "1.8"
}

tasks.withType<io.gitlab.arturbosch.detekt.DetektCreateBaselineTask>().configureEach {
    jvmTarget = "1.8"
}

tasks.register("runOnGitHub") {
    dependsOn(
        "detekt",
        ":common:database:testDebugUnitTest",
        ":common:network:testDebugUnitTest",
        ":common:settings:testDebugUnitTest",
        ":android:lint"
    )
    group = "custom"
    description = "./gradlew runOnGitHub # runs on GitHub Action"
}
