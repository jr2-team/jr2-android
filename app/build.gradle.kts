plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}
android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "io.github.jr2team.jr2android"
        minSdkVersion(19)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    sourceSets {
        val main by getting
        main.java.srcDirs("src/main/kotlin")
        val test by getting
        test.java.srcDirs("src/test/kotlin")
        val androidTest by getting
        androidTest.java.srcDirs("src/androidTest/kotlin")
    }
}
dependencies {
    // Core
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.40")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.3.40")
    implementation("androidx.appcompat:appcompat:1.0.2")
    implementation("androidx.multidex:multidex:2.0.1")
    implementation("com.google.android.material:material:1.1.0-alpha07")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    // Jet Pack - Lifecycle
    val lifecycleVersion = "2.0.0"
    implementation("androidx.lifecycle:lifecycle-extensions:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    kapt("androidx.lifecycle:lifecycle-compiler:$lifecycleVersion")
    // Jet Pack - Layout
    implementation("androidx.constraintlayout:constraintlayout:2.0.0-beta2")
    // Jet Pack - Navigation
    val navigationVersion = "2.1.0-alpha05"
    implementation("androidx.navigation:navigation-fragment-ktx:$navigationVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navigationVersion")
    // Jet Pack - Room
    val roomVersion = "2.1.0"
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    // Anko - Commons
    val ankoVersion = "0.10.8"
    implementation("org.jetbrains.anko:anko-commons:$ankoVersion")
    implementation("org.jetbrains.anko:anko-sdk25:$ankoVersion")
    // sdk15, sdk19, sdk21, sdk23 are also available
    implementation("org.jetbrains.anko:anko-appcompat-v7:$ankoVersion")
    implementation("org.jetbrains.anko:anko-sdk25-coroutines:$ankoVersion")
    implementation("org.jetbrains.anko:anko-appcompat-v7-coroutines:$ankoVersion")
    implementation("org.jetbrains.anko:anko-sqlite:$ankoVersion")
    // Anko - Support library
    implementation("org.jetbrains.anko:anko-appcompat-v7-commons:$ankoVersion")
    implementation("org.jetbrains.anko:anko-appcompat-v7:$ankoVersion")
    implementation("org.jetbrains.anko:anko-coroutines:$ankoVersion")
    implementation("org.jetbrains.anko:anko-cardview-v7:$ankoVersion")
    implementation("org.jetbrains.anko:anko-design:$ankoVersion")
    implementation("org.jetbrains.anko:anko-design-coroutines:$ankoVersion")
    implementation("org.jetbrains.anko:anko-gridlayout-v7:$ankoVersion")
    implementation("org.jetbrains.anko:anko-percent:$ankoVersion")
    implementation("org.jetbrains.anko:anko-recyclerview-v7:$ankoVersion")
    implementation("org.jetbrains.anko:anko-recyclerview-v7-coroutines:$ankoVersion")
    implementation("org.jetbrains.anko:anko-support-v4-commons:$ankoVersion")
    implementation("org.jetbrains.anko:anko-support-v4:$ankoVersion")
    implementation("org.jetbrains.anko:anko-constraint-layout:$ankoVersion")
    // Kotlin's coroutines
    val coroutinesVersion = "1.1.1"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
    // OkHttp
    implementation("com.squareup.okhttp3:okhttp:3.12.0")
    // Retrofit2
    val retrofit2Version = "2.6.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofit2Version")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofit2Version")
    // Moshi
    val moshiVersion = "1.8.0"
    implementation("com.squareup.moshi:moshi:$moshiVersion")
    implementation("com.squareup.moshi:moshi-adapters:$moshiVersion")
    implementation("com.squareup.moshi:moshi-kotlin:$moshiVersion")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion")
    // Okio
    implementation("com.squareup.okio:okio:2.1.0")
    // StickyHeaders
    implementation("org.zakariya.stickyheaders:stickyheaders:0.7.6")
    // ReactiveX
    implementation("io.reactivex.rxjava2:rxandroid:2.1.0")
    implementation("io.reactivex.rxjava2:rxjava:2.2.3")
    implementation("io.reactivex.rxjava2:rxkotlin:2.3.0")

    testImplementation("junit:junit:4.12")
    testImplementation("androidx.room:room-testing:$roomVersion")
    androidTestImplementation("com.android.support.test.espresso:espresso-core:3.0.2")
    androidTestImplementation("com.android.support.test.espresso:espresso-contrib:3.0.2")
}

kapt {
    generateStubs = true
    correctErrorTypes = true
}
