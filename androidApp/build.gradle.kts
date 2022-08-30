plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 32
    defaultConfig {
        applicationId = "io.github.jacksonweekes.xviewer.android"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(libs.material)
    implementation(libs.appcompat)
    implementation(libs.constraintlayout)
    implementation(libs.ktor.client.okHttp)
    implementation(libs.bundles.app.ui)
}