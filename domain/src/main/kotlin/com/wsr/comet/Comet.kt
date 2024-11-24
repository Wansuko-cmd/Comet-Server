package com.wsr.comet

import java.util.*

data class Comet(
    val id: CometId = CometId(UUID.randomUUID().toString()),
    val core: Core,
    val coma: Coma,
    val tails: List<Tail>,
)

@JvmInline
value class CometId(val value: String)
