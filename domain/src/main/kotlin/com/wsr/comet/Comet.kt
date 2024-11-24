package com.wsr.comet

import com.wsr.user.UserId
import java.util.*

data class Comet(
    val id: CometId = CometId(UUID.randomUUID().toString()),
    val core: Core,
    val coma: Coma,
    val tails: Map<UserId, List<Tail>>,
)

@JvmInline
value class CometId(val value: String)
