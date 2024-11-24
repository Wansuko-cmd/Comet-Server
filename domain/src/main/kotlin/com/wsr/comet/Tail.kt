package com.wsr.comet

import com.wsr.user.UserId
import java.util.*

data class Tail(
    val tailId: TailId,
    val observerId: UserId.ObserverId,
    val dusts: List<Dust>,
)

data class Dust(
    val id: DustId = DustId(UUID.randomUUID().toString()),
    val posterType: Position,
    val content: Content,
)

@JvmInline
value class TailId(val value: String)

@JvmInline
value class DustId(val value: String)

sealed interface Position {
    data object Owner : Position
    data object Observer : Position
}
