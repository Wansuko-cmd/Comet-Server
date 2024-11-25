package com.wsr.comet

import java.util.UUID

data class Coma(
    val id: ComaId = ComaId(UUID.randomUUID().toString()),
    val content: Content,
)

@JvmInline
value class ComaId(
    val value: String,
)
