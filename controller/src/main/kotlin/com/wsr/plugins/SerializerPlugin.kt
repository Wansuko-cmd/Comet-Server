package com.wsr.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

fun Application.configureSerializerPlugin() {
    install(ContentNegotiation) {
        json()
    }
}
