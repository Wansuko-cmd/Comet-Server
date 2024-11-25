package com.wsr.routing.users

import com.wsr.mapBoth
import com.wsr.routing.getRequest
import com.wsr.user.CreateUserUseCase
import com.wsr.user.UserName
import io.ktor.http.HttpStatusCode
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.util.reflect.TypeInfo
import kotlinx.serialization.Serializable
import org.koin.ktor.ext.inject

fun Route.usersIndexPost() {
    val createUserUseCase: CreateUserUseCase by inject()

    post("") {
        call
            .getRequest<UsersIndexPostRequest>()
            .let { (username) -> createUserUseCase(UserName(username)) }
            .mapBoth(
                success = { call.respond(HttpStatusCode.OK, typeInfo = TypeInfo(Unit::class)) },
                failure = { call.respond(HttpStatusCode.BadRequest, typeInfo = TypeInfo(Unit::class)) },
            )
    }
}

@Serializable
private data class UsersIndexPostRequest(
    val username: String,
)
