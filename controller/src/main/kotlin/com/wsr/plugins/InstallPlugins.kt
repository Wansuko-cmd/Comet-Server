package com.wsr.plugins

import io.ktor.server.application.Application

fun Application.installPlugins() {
    configureKoinPlugin()
    configureSerializerPlugin()
}
