plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":utils"))

    implementation(libs.kotlin.coroutine)
    implementation(libs.kotlin.datetime)
}
