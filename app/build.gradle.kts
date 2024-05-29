plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.underdogs.cookingrecipe"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.underdogs.cookingrecipe"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material) // Use this if you want stable version
    // implementation(libs.materialAlpha) // Use this if you need alpha version features
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.activity)
    implementation(libs.volley)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.picasso)
    implementation("com.squareup.okhttp3:okhttp:4.9.0")
    implementation("com.google.code.gson:gson:2.8.6")
    implementation("com.android.volley:volley:1.2.0")

}