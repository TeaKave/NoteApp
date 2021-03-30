plugins {
    id("java-library")
    kotlin("jvm")
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(":domain"))
    implementation(Libs.coroutines)
    implementation(Libs.kotlin)
    implementation(Libs.interactor)

    testImplementation(TestLibs.mockito)
    testImplementation(TestLibs.kotestRunner)
    testImplementation(TestLibs.kotestAssertions)
    testImplementation(TestLibs.kotestProperty)
}
