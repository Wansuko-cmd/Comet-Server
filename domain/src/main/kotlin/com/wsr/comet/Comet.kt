package com.wsr.comet

import com.wsr.user.UserId
import java.util.UUID

sealed interface Comet {
    val id: CometId
    val ownerId: UserId
    val core: Core
    val coma: Coma?
    val tails: List<Tail>

    data class Owned(
        override val id: CometId,
        override val ownerId: UserId,
        override val core: Core,
        override val coma: Coma?,
        override val tails: List<Tail>,
    ) : Comet {
        init {
            require(tails.none { it.observerId == ownerId })
        }

        companion object {
            fun create(
                ownerId: UserId,
                core: Core,
            ) = Owned(
                id = CometId(UUID.randomUUID().toString()),
                ownerId = ownerId,
                core = core,
                coma = null,
                tails = emptyList(),
            )

            fun reconstruct(
                id: CometId,
                ownerId: UserId,
                core: Core,
                coma: Coma?,
                tails: List<Tail>,
            ) = Owned(
                id = id,
                ownerId = ownerId,
                core = core,
                coma = coma,
                tails = tails,
            )
        }
    }

    data class Observable(
        override val id: CometId,
        override val ownerId: UserId,
        override val core: Core,
        override val coma: Coma?,
        override val tails: List<Tail>,
    ) : Comet {
        init {
            require(tails.size <= 1)
            require(tails.none { it.observerId == ownerId })
        }

        companion object {
            fun reconstruct(
                id: CometId,
                ownerId: UserId,
                core: Core,
                coma: Coma?,
                tail: Tail?,
            ) = Observable(
                id = id,
                ownerId = ownerId,
                core = core,
                coma = coma,
                tails = listOfNotNull(tail),
            )
        }
    }
}

@JvmInline
value class CometId(
    val value: String,
)
