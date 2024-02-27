plugins {
  id("com.android.application")
  id("kotlin-android")
  id("kotlin-kapt")

  id("com.google.dagger.hilt.android")
}

android {
  namespace = "com.teixeira.usercrud"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.teixeira.usercrud"
    minSdk = 21
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  buildTypes {
    release {
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }

  buildFeatures { viewBinding = true }

  kapt { correctErrorTypes = true }
}

dependencies {
  implementation("androidx.appcompat:appcompat:1.6.1")
  implementation("com.google.android.material:material:1.9.0")

  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

  implementation("androidx.core:core-ktx:1.10.0")
  implementation("androidx.fragment:fragment-ktx:1.5.7")
  implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
  implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")

  val navigationVersion = "2.5.3"
  implementation("androidx.navigation:navigation-fragment-ktx:$navigationVersion")
  implementation("androidx.navigation:navigation-ui-ktx:$navigationVersion")
  implementation("androidx.navigation:navigation-dynamic-features-fragment:$navigationVersion")

  val roomVersion = "2.6.1"
  kapt("androidx.room:room-compiler:$roomVersion")
  implementation("androidx.room:room-runtime:$roomVersion")
  implementation("androidx.room:room-ktx:$roomVersion")

  val hiltVersion = "2.50"
  kapt("com.google.dagger:hilt-compiler:$hiltVersion")
  implementation("com.google.dagger:hilt-android:$hiltVersion")
}