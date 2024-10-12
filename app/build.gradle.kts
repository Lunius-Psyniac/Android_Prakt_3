plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.android_prakt_3"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.android_prakt_3"
        minSdk = 29
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.datastore.preferences.rxjava2)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("androidx.datastore:datastore-rxjava2:1.1.1")
    implementation("io.reactivex.rxjava2:rxjava:2.2.21") // or the latest version
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1") // or the latest version
    implementation("androidx.datastore:datastore-rxjava2:1.0.0") // Datastore RxJava2 dependency

}