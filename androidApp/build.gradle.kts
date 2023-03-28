plugins {
    kotlin("multiplatform")
    id("com.android.application")
    id("org.jetbrains.compose")
}

kotlin {
    android()
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(project(":shared"))

                implementation("androidx.compose.ui:ui:1.2.1")
                implementation("androidx.compose.ui:ui-tooling:1.2.1")
                implementation("androidx.compose.ui:ui-tooling-preview:1.2.1")
                implementation("androidx.compose.foundation:foundation:1.2.1")
                implementation("androidx.compose.material:material:1.2.1")
                implementation("androidx.activity:activity-compose:1.5.1")
            }
        }
    }
}

android {
    namespace = "com.example.demoapppodlodka.android"
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/main/AndroidManifest.xml")
    defaultConfig {
        applicationId = "com.example.demoapppodlodka.android"
        minSdk = 29
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}