package com.wsr.routing.user

import io.ktor.server.routing.*

fun Route.userRoute() {
    route("/user") {
        userIndexPost()
    }
}
