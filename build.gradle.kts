plugins {
    alias(libs.plugins.multiplatform).apply(false)
    alias(libs.plugins.compose).apply(false)
    alias(libs.plugins.cocoapods).apply(false)
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.kotlinx.parcelize).apply(false)
    alias(libs.plugins.kotlinx.serialization).apply(false)
    alias(libs.plugins.libres).apply(false)
    alias(libs.plugins.sqlDelight).apply(false)
    id("io.gitlab.arturbosch.detekt") version "1.22.0"
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
