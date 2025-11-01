<<<<<<< HEAD
// 🔹 루트 수준 build.gradle.kts
// 공통 설정 및 하위 모듈에서 사용하는 플러그인 버전 정의
=======
// Top-level build file where you can add configuration options common to all sub-projects/modules.
>>>>>>> dev

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.ksp) apply false
}