plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(Android.compileSdk)

    defaultConfig {
        minSdkVersion(Android.minSdk)
        targetSdkVersion(Android.targetSdk)

        versionCode = Android.versionCode
        versionName = "${project.version}"

        testInstrumentationRunner = Android.testInstrumentRunner
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("test").java.srcDirs("src/test/kotlin")
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(Libs.kotlin)
    implementation(Libs.ktxCore)
    implementation(Libs.appcompat)
    api(Libs.room)
    implementation(Libs.roomKtx)
    kapt(Libs.roomCompiler)
    implementation(Libs.interactor)

    testImplementation(TestLibs.junit)
    testImplementation(TestLibs.kotestRunner)
    testImplementation(TestLibs.kotestAssertions)
    testImplementation(TestLibs.kotestProperty)
    testImplementation(TestLibs.mockito)
    androidTestImplementation(TestLibs.junitExtension)
    androidTestImplementation(TestLibs.espresso)

}