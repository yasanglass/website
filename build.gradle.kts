plugins {
    alias(libs.plugins.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.kotlin.compose)
    alias(libs.plugins.jetbrains.compose)
}

kotlin {
    js(IR) {
        browser()
        binaries.executable()
    }

    sourceSets {
        jsMain.dependencies {
            implementation(libs.jetbrains.compose.html.core)
            implementation(libs.jetbrains.compose.runtime)
        }
    }
}
