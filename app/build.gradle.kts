plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android-extensions")
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

    }

    testBuildType = "debug"

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
    implementation(project(":infrastructure"))

    implementation(Libs.googleAuth)
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

    testImplementation(TestLibs.junit)
    androidTestImplementation(TestLibs.junitExtension)
    androidTestImplementation(TestLibs.espresso)

}