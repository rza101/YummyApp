plugins {
    id("com.android.application")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.android")
}

apply("../shared_dependencies.gradle")

android {
    namespace = "com.rhezarijaya.yummyapp"
    compileSdk = 34

    buildFeatures {
        viewBinding = true
    }

    defaultConfig {
        applicationId = "com.rhezarijaya.yummyapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isDebuggable = true
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            isDebuggable = false
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    dynamicFeatures += setOf(":favorite")
}

dependencies {
    implementation(project(":core"))

    // navigation component
    api("androidx.navigation:navigation-fragment-ktx:2.7.6")
    api("androidx.navigation:navigation-ui-ktx:2.7.6")
    api("androidx.navigation:navigation-dynamic-features-fragment:2.7.6")

    // swipe refresh layout
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
}