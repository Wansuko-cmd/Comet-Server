package com.wsr.comet

import com.wsr.user.UserId
import java.util.UUID

data class Tail(
    val id: TailId,
    val observerId: UserId.ObserverId,
    val dusts: List<Dust>,
)

data class Dust(
    val id: DustId = DustId(UUID.randomUUID().toString()),
    val position: Position,
    val content: Content,
)

@JvmInline
value class TailId(
    val value: String,
)

@JvmInline
value class DustId(
    val value: String,
)

sealed interface Position {
    data object Owner : Position

    data object Observer : Position
}
