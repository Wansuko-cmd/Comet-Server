package com.wsr.routing.users

import io.ktor.server.routing.Route
import io.ktor.server.routing.route

fun Route.usersRoute() {
    route("/users") {
        usersIndexPost()
    }
}
