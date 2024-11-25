package com.wsr.routing.owner

import com.wsr.routing.owner.comets.ownerCometsIndexGet
import com.wsr.routing.owner.user.ownerUsersIndexGet
import io.ktor.server.routing.Route
import io.ktor.server.routing.route

fun Route.ownerRoute() {
    route("/owner") {
        ownerCometsIndexGet()
        ownerUsersIndexGet()
    }
}
