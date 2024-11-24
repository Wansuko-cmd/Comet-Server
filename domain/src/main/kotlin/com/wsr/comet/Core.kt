package com.wsr.comet

import com.wsr.user.UserId
import java.util.*

data class Core(
    val id: CoreId = CoreId(UUID.randomUUID().toString()),
    val userId: UserId.OwnerId,
    val content: Content,
)

@JvmInline
value class CoreId(val value: String)
