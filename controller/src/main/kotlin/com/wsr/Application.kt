package com.wsr

import com.wsr.plugins.installPlugins
import com.wsr.routing.mainRoute
import io.ktor.server.application.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    installPlugins()
    mainRoute()
}
