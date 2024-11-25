package com.wsr.routing.comets

import com.wsr.comet.Content
import com.wsr.comet.CreateCometError
import com.wsr.comet.CreateCometUseCase
import com.wsr.mapBoth
import com.wsr.routing.getRequest
import com.wsr.user.UserId
import io.ktor.http.HttpStatusCode
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.util.reflect.TypeInfo
import kotlinx.serialization.Serializable
import org.koin.ktor.ext.inject

fun Route.cometsIndexPost() {
    val createCometUseCase: CreateCometUseCase by inject()

    get("") {
        call
            .getRequest<CometsIndexPostRequest>()
            .let { (ownerId, content) ->
                createCometUseCase(
                    ownerId = UserId(ownerId),
                    content = Content.Text(content),
                )
            }.mapBoth(
                success = {
                    call.respond(
                        message = HttpStatusCode.OK,
                        typeInfo = TypeInfo(Unit::class),
                    )
                },
                failure = { error ->
                    when (error) {
                        CreateCometError.InternalServerError -> {
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
private data class CometsIndexPostRequest(
    val ownerId: String,
    val content: String,
)
