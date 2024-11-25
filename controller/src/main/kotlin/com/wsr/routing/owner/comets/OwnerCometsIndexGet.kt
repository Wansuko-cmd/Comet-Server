package com.wsr.routing.owner.comets

import com.wsr.comet.Coma
import com.wsr.comet.Core
import com.wsr.comet.Dust
import com.wsr.comet.GetOwnedComet
import com.wsr.comet.GetOwnedCometsUseCase
import com.wsr.comet.Position
import com.wsr.comet.Tail
import com.wsr.mapBoth
import com.wsr.routing.getRequest
import com.wsr.user.User
import com.wsr.user.UserId
import io.ktor.http.HttpStatusCode
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.util.reflect.TypeInfo
import kotlinx.serialization.Serializable
import org.koin.ktor.ext.inject

fun Route.ownerCometsIndexGet() {
    val getOwnedCometsUseCase: GetOwnedCometsUseCase by inject()

    get("") {
        call
            .getRequest<OwnerCometsIndexGetRequest>()
            .let { (ownerId, page) ->
                getOwnedCometsUseCase(
                    ownerId = UserId.OwnerId(ownerId),
                    page = page,
                )
            }.mapBoth(
                success = { comets ->
                    call.respond(
                        message = OwnerCometsIndexGetResponse.from(comets),
                        typeInfo = TypeInfo(OwnerCometsIndexGetResponse::class),
                    )
                },
                failure = {
                    call.respond(
                        message = HttpStatusCode.BadRequest,
                        typeInfo = TypeInfo(Unit::class),
                    )
                },
            )
    }
}

@Serializable
private data class OwnerCometsIndexGetRequest(
    val ownerId: String,
    val page: Int = 0,
)

@Serializable
private data class OwnerCometsIndexGetResponse(
    val comets: List<ResponseOwnedComet>,
) {
    companion object {
        fun from(comets: List<GetOwnedComet>) = comets.map { comet -> ResponseOwnedComet.from(comet) }
    }
}

@Serializable
private data class ResponseOwnedComet(
    val id: String,
    val ownerUser: ResponseOwnerUser,
    val core: ResponseCore,
    val coma: ResponseComa?,
    val tails: List<ResponseTail>,
) {
    companion object {
        fun from(comet: GetOwnedComet): ResponseOwnedComet =
            ResponseOwnedComet(
                id = comet.id.value,
                ownerUser = ResponseOwnerUser.from(comet.ownerUser),
                core = ResponseCore.from(comet.core),
                coma = ResponseComa.from(comet.coma),
                tails = comet.tails.map { ResponseTail.from(it) },
            )
    }
}

@Serializable
private data class ResponseOwnerUser(
    val id: String,
    val name: String,
) {
    companion object {
        fun from(user: User) =
            ResponseOwnerUser(
                id = user.id.value,
                name = user.name.value,
            )
    }
}

@Serializable
private data class ResponseCore(
    val id: String,
    val content: String,
) {
    companion object {
        fun from(core: Core) =
            ResponseCore(
                id = core.id.value,
                content = core.content.toString(),
            )
    }
}

@Serializable
private data class ResponseComa(
    val id: String,
    val content: String,
) {
    companion object {
        fun from(coma: Coma?) =
            coma?.let {
                ResponseComa(
                    id = it.id.value,
                    content = it.content.toString(),
                )
            }
    }
}

@Serializable
private data class ResponseTail(
    val id: String,
    val observerId: String,
    val dusts: List<ResponseDust>,
) {
    companion object {
        fun from(tail: Tail) =
            ResponseTail(
                id = tail.id.value,
                observerId = tail.observerId.value,
                dusts = tail.dusts.map { ResponseDust.from(it) },
            )
    }
}

@Serializable
private data class ResponseDust(
    val id: String,
    val position: ResponsePosition,
    val content: String,
) {
    companion object {
        fun from(dust: Dust) =
            ResponseDust(
                id = dust.id.value,
                position = ResponsePosition.from(dust.position),
                content = dust.content.toString(),
            )
    }
}

@Serializable
private sealed interface ResponsePosition {
    data object Owner : ResponsePosition

    data object Observer : ResponsePosition

    companion object {
        fun from(position: Position) =
            when (position) {
                is Position.Owner -> Owner
                is Position.Observer -> Observer
            }
    }
}
