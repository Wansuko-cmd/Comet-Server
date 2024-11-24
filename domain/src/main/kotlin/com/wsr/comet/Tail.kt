package com.wsr.comet

import java.util.*

data class Tail(
    val id: TailId = TailId(UUID.randomUUID().toString()),
    val posterType: Position,
    val content: Content,
)

@JvmInline
value class TailId(val value: String)

sealed interface Position {
    data object Owner : Position
    data object Observer : Position
}
