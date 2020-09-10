plugins {
    id("java-library")
    kotlin("jvm")
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libs.coroutines)
    implementation(Libs.kotlin)
}
