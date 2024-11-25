package com.wsr.routing

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.plugins.BadRequestException
import io.ktor.server.plugins.CannotTransformContentToTypeException
import io.ktor.server.request.receive
import io.ktor.util.reflect.TypeInfo

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
