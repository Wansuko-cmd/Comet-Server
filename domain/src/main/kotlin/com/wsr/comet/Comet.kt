package com.wsr.comet

import com.wsr.user.UserId
import java.util.UUID

data class Comet(
    val id: CometId = CometId(UUID.randomUUID().toString()),
    val ownerId: UserId,
    val core: Core,
    val coma: Coma? = null,
    val tails: List<Tail> = emptyList(),
) {
    init {
        require(tails.none { it.observerId == ownerId })
        require(tails.distinct().size == tails.size)
    }

    fun lookIn(observerId: UserId): Comet =
        when {
            observerId == ownerId -> this
            else -> this.copy(tails = tails.filter { it.observerId == observerId })
        }
}

@JvmInline
value class CometId(
    val value: String,
)
