import org.gradle.api.JavaVersion

object Android {
    const val applicationId = "com.teakave.noteapp"

    const val compileSdk = 30
    const val minSdk = 21
    const val targetSdk = 30

    const val versionCode = 1
    const val versionName = "1.0.0"

    const val testInstrumentRunner = "androidx.test.runner.AndroidJUnitRunner"

    val sourceCompatibilityJava = JavaVersion.VERSION_1_8
    val targetCompatibilityJava = JavaVersion.VERSION_1_8

}

object GradlePlugins {
    const val androidGradle = "com.android.tools.build:gradle:${Versions.gradleBuildTools}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.safeArgs}"
}


object Versions {
    const val gradleBuildTools = "4.0.0"
    const val kotlin = "1.3.50"
    const val appcompat = "1.2.0"

    /* test */
    const val junit = "4.13"
    const val kotest = "4.2.4"
    const val mockito = "1.10.0"

    const val ktxCore = "1.3.1"
    const val constraintLayout = "2.0.0"
    const val navigation = "2.3.0"
    const val legacySupport = "1.0.0"
    const val recyclerView = "1.1.0"
    const val material = "1.2.1"
    const val annotation = "1.1.0"
    const val lifecycle = "2.2.0"
    const val junitExtension = "1.1.1"
    const val espresso = "3.2.0"
    const val coroutines = "1.3.7"
    const val koin = "2.1.6"
    const val room = "2.2.5"
    const val safeArgs = "2.3.0"
    const val leakCanary = "2.4"
}

object Libs {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val ktxCore = "androidx.core:core-ktx:${Versions.ktxCore}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val navigation = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val legacySupport = "androidx.legacy:legacy-support-v4:${Versions.legacySupport}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val annotation = "androidx.annotation:annotation:${Versions.annotation}"
    const val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val koinAndroid = "org.koin:koin-android:${Versions.koin}"
    const val koinScope = "org.koin:koin-androidx-scope:${Versions.koin}"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"

}

object TestLibs {
    const val junit = "junit:junit:${Versions.junit}"
    const val junitExtension = "androidx.test.ext:junit:${Versions.junitExtension}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val kotestRunner = "io.kotest:kotest-runner-junit5:${Versions.kotest}"
    const val kotestAssertions = "io.kotest:kotest-assertions-core:${Versions.kotest}"
    const val kotestProperty = "io.kotest:kotest-property:${Versions.kotest}"
    const val mockito = "io.mockk:mockk:${Versions.mockito}"
}
