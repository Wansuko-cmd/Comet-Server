package com.wsr.routing.owner.user

import com.wsr.mapBoth
import com.wsr.routing.getRequest
import com.wsr.user.GetUserError
import com.wsr.user.GetUserUseCase
import com.wsr.user.User
import com.wsr.user.UserId
import io.ktor.http.HttpStatusCode
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.util.reflect.TypeInfo
import kotlinx.serialization.Serializable
import org.koin.ktor.ext.inject

fun Route.ownerUsersIndexGet() {
    val getUserUseCase: GetUserUseCase by inject()

    get("") {
        call
            .getRequest<OwnerUsersIndexGetRequest>()
            .let { (ownerId) -> getUserUseCase(UserId(ownerId)) }
            .mapBoth(
                success = { user ->
                    call.respond(
                        message = OwnerUsersIndexGetResponse.from(user),
                        typeInfo = TypeInfo(Unit::class),
                    )
                },
                failure = { error ->
                    when (error) {
                        GetUserError.InternalServerError -> {
                            call.respond(
                                message = HttpStatusCode.InternalServerError,
                                typeInfo = TypeInfo(Unit::class),
                            )
                        }
                    }
                },
            )
    }
}

@Serializable
private data class OwnerUsersIndexGetRequest(
    val ownerId: String,
)

@Serializable
private data class OwnerUsersIndexGetResponse(
    val id: String,
    val name: String,
) {
    companion object {
        fun from(user: User) =
            OwnerUsersIndexGetResponse(
                id = user.id.value,
                name = user.name.value,
            )
    }
}
