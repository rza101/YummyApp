plugins {
    id("com.android.library")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
    id("org.jetbrains.kotlin.android")
}

apply("../shared_dependencies.gradle")

android {
    namespace = "com.rhezarijaya.core"
    compileSdk = 34

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField(
            "String",
            "THE_MEAL_DB_BASE_URL",
            "\"https://www.themealdb.com/api/json/v1/\""
        )
        buildConfigField(
            "String",
            "THE_MEAL_DB_HOSTNAME",
            "\"www.themealdb.com\""
        )
        buildConfigField(
            "String",
            "THE_MEAL_DB_PIN_1",
            "\"sha256/SixxdJm2Bb2eag6Iz05yDKCIRIaK9P629RlokEfmHLQ=\""
        )
        buildConfigField(
            "String",
            "THE_MEAL_DB_PIN_2",
            "\"sha256/81Wf12bcLlFHQAfJluxnzZ6Frg+oJ9PWY/Wrwur8viQ=\""
        )
        buildConfigField(
            "String",
            "THE_MEAL_DB_PIN_3",
            "\"sha256/hxqRlPTu1bMS/0DITB1SSu0vd4u/8l8TjPgfaAp63Gc=\""
        )
        buildConfigField("String", "THE_MEAL_DB_API_KEY", "\"1\"")
    }

    buildTypes {
        debug {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
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
}

dependencies {
    // retrofit
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // room
    ksp("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.sqlite:sqlite-ktx:2.4.0")
    implementation("net.zetetic:android-database-sqlcipher:4.4.0")
}