package com.wsr.comet

import com.wsr.user.UserId
import java.util.*

data class Comet(
    val id: CometId = CometId(UUID.randomUUID().toString()),
    val ownerId: UserId.OwnerId,
    val core: Core,
    val coma: Coma,
    val tails: List<Tail>,
)

@JvmInline
value class CometId(val value: String)
