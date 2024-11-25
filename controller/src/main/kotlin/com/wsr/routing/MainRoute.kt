package com.wsr.routing

import com.wsr.routing.users.usersRoute
import io.ktor.server.application.Application
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun Application.mainRoute() {
    routing {
        get("") { call.respond("Success", typeInfo = null) }
        usersRoute()
    }
}
