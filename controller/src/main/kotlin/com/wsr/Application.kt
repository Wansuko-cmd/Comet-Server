package com.wsr

import com.wsr.plugins.installPlugins
import com.wsr.routing.mainRoute
import io.ktor.server.application.Application
import io.ktor.server.netty.EngineMain

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    installPlugins()
    mainRoute()
}
