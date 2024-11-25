package com.wsr.routing.owner

import com.wsr.routing.owner.comets.ownerCometsIndexGet
import io.ktor.server.routing.Route
import io.ktor.server.routing.route

fun Route.ownerRoute() {
    route("/owner") {
        ownerCometsIndexGet()
    }
}
