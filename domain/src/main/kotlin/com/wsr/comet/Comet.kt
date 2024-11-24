package com.wsr.comet

import com.wsr.user.UserId
import java.util.*

data class Comet(
    val id: CometId = CometId(UUID.randomUUID().toString()),
    val ownerId: UserId.OwnerId,
    val core: Core,
    val coma: Coma? = null,
    val tails: List<Tail> = emptyList(),
)

@JvmInline
value class CometId(val value: String)
