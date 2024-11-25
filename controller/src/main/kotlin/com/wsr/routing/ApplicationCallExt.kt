package com.wsr.routing

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.util.reflect.*

suspend inline fun <reified T : Any> ApplicationCall.getRequest(): T =
    try {
        receive<T>()
    } catch (e: CannotTransformContentToTypeException) {
        respond(HttpStatusCode.BadRequest, typeInfo = TypeInfo(Unit::class))
        throw e
    } catch (e: BadRequestException) {
        respond(HttpStatusCode.BadRequest, typeInfo = TypeInfo(Unit::class))
        throw e
    }
