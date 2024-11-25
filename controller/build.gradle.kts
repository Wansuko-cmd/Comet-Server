plugins {
    kotlin("jvm")
    alias(libs.plugins.serialization)
    alias(libs.plugins.ktor)
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":usecase"))
    implementation(project(":data"))
    implementation(project(":utils"))

    implementation(libs.kotlin.coroutine)
    implementation(libs.kotlin.datetime)

    implementation(libs.kotlin.serialization)
    implementation(libs.bundles.ktor)
    implementation(platform(libs.koin.bom))
    implementation(libs.bundles.koin)
}
