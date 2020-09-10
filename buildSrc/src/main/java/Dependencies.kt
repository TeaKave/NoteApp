object Android {
    const val applicationId = "com.teakave.noteapp"

    const val compileSdk = 30
    const val minSdk = 21
    const val targetSdk = 30

    const val versionCode = 1
    const val versionName = "1.0.0"

    const val testInstrumentRunner = "androidx.test.runner.AndroidJUnitRunner"

}

object Versions {
    const val gradle = "4.0.1"
    const val kotlin = "1.3.50"
    const val appcompat = "1.2.0"
    /* test */
    const val junit = "4.13"

    const val googleAuth = "18.1.0"

    const val ktxCore = "1.3.1"
    const val constraintLayout = "1.1.3"
    const val navigation = "2.3.0"
    const val legacySupport = "1.0.0"
    const val recyclerView = "1.1.0"
    const val material = "1.2.0"
    const val annotation = "1.1.0"
    const val lifecycle = "2.2.0"
    const val junitExtension = "1.1.1"
    const val espresso = "3.2.0"
    const val coroutines = "1.3.7"
}

object Libs {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val ktxCore = "androidx.core:core-ktx:${Versions.ktxCore}"
    const val googleAuth = "com.google.android.gms:play-services-auth:${Versions.googleAuth}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val navigation = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val legacySupport = "androidx.legacy:legacy-support-v4:${Versions.legacySupport}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val annotation = "androidx.annotation:annotation:${Versions.annotation}"
    const val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
}

object TestLibs {
    const val junit = "junit:junit:${Versions.junit}"
    const val junitExtension = "androidx.test.ext:junit:${Versions.junitExtension}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}
