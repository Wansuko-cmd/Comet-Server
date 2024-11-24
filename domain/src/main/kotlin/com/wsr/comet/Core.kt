package com.wsr.comet

import java.util.*

data class Core(
    val id: CoreId = CoreId(UUID.randomUUID().toString()),
    val content: Content,
)

@JvmInline
value class CoreId(val value: String)
