package com.wsr.routing.user

import io.ktor.server.routing.Route
import io.ktor.server.routing.route

fun Route.userRoute() {
    route("/user") {
        userIndexPost()
    }
}
