plugins {
    id("com.android.dynamic-feature")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.android")
}

apply("../shared_dependencies.gradle")

android {
    namespace = "com.rhezarijaya.favorite"
    compileSdk = 34

    buildFeatures {
        viewBinding = true
    }

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":app"))
    implementation(project(":core"))
}