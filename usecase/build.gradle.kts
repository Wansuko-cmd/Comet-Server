plugins {
    kotlin("jvm")
}

dependencies {
    implementation(":domain")
    implementation(":utils")

    implementation(libs.kotlin.coroutine)
    implementation(libs.kotlin.datetime)
}
