package com.wsr.routing

import com.wsr.routing.user.userRoute
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.mainRoute() {
    routing {
        get("") { call.respond("Success", typeInfo = null) }
        userRoute()
    }
}
