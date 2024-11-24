package com.wsr.comet

import com.wsr.user.UserId
import java.util.*

sealed interface Comet {
    val id: CometId
    val ownerId: UserId.OwnerId
    val core: Core
    val coma: Coma?

    data class Owned(
        override val id: CometId = CometId(UUID.randomUUID().toString()),
        override val ownerId: UserId.OwnerId,
        override val core: Core,
        override val coma: Coma? = null,
        val tails: List<Tail> = emptyList(),
    ) : Comet

    data class Observable(
        override val id: CometId = CometId(UUID.randomUUID().toString()),
        override val ownerId: UserId.OwnerId,
        override val core: Core,
        override val coma: Coma? = null,
    ) : Comet

    companion object {
        fun create(ownerId: UserId.OwnerId, core: Core) = Owned(
            ownerId = ownerId,
            core = core,
        )
    }
}

@JvmInline
value class CometId(val value: String)
