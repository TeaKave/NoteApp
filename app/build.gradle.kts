plugins {
    id("com.android.application")
    kotlin("android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(Android.compileSdk)

    defaultConfig {
        applicationId = Android.applicationId

        minSdkVersion(Android.minSdk)
        targetSdkVersion(Android.targetSdk)

        versionCode = Android.versionCode
        versionName = Android.versionName

        testInstrumentationRunner = Android.testInstrumentRunner

        manifestPlaceholders = mapOf("libId" to Android.applicationId)
    }

    buildFeatures {
        viewBinding = true
    }

    testBuildType = "debug"

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("test").java.srcDirs("src/test/kotlin")
    }

    compileOptions {
        sourceCompatibility = Android.sourceCompatibilityJava
        targetCompatibility = Android.targetCompatibilityJava
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":infrastructure"))

    implementation(Libs.kotlin)
    implementation(Libs.ktxCore)
    implementation(Libs.appcompat)
    implementation(Libs.constraintLayout)
    implementation(Libs.navigation)
    implementation(Libs.navigationUi)
    implementation(Libs.legacySupport)
    implementation(Libs.recyclerView)
    implementation(Libs.material)
    implementation(Libs.annotation)
    implementation(Libs.lifecycle)
    implementation(Libs.koinScope)
    implementation(Libs.koinViewModel)
    implementation(Libs.koinAndroid)

    debugImplementation(Libs.leakCanary)

    testImplementation(TestLibs.junit)
    testImplementation(TestLibs.kotestRunner)
    testImplementation(TestLibs.kotestAssertions)
    testImplementation(TestLibs.kotestProperty)
    testImplementation(TestLibs.mockito)
    androidTestImplementation(TestLibs.junitExtension)
    androidTestImplementation(TestLibs.espresso)

}