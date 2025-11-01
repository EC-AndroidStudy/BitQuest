plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    // Hilt 플러그인 제거
    // id("com.google.dagger.hilt.android")
    id("kotlin-kapt") // kapt는 다른 라이브러리에서 사용할 수 있으므로 일단 유지
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.largeblueberry.bitquest"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.largeblueberry.bitquest"
        minSdk = 35
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // setContent 함수를 사용하기 위한 라이브러리 추가
    implementation("androidx.activity:activity-compose:1.10.1")

    // Compose (UI 툴킷)
    implementation(platform("androidx.compose:compose-bom:2024.05.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended:1.6.7")

    // Hilt 의존성 제거
    // implementation("com.google.dagger:hilt-android:2.51.1")
    // kapt("com.google.dagger:hilt-compiler:2.51.1")
    // implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

kapt {
    correctErrorTypes = true
}
