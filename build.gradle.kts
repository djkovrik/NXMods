plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    kotlin("multiplatform").apply(false)
    id("com.android.application").apply(false)
    id("com.android.library").apply(false)
    id("org.jetbrains.compose").apply(false)
    id("org.jetbrains.kotlin.plugin.serialization").apply(false)
    id("com.squareup.sqldelight").apply(false)
    id("io.github.skeptick.libres").apply(false)
    id("io.gitlab.arturbosch.detekt") version "1.22.0-RC2"
}

detekt {
    buildUponDefaultConfig = true
    allRules = false
    source = files("$projectDir/shared/")
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

tasks.register<GradleBuild>("runOnGitHub") {
    tasks = listOf("detekt")
}
