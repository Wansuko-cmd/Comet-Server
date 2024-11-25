plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.kotlin.coroutine)
    implementation(libs.kotlin.datetime)
}
