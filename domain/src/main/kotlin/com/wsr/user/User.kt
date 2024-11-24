package com.wsr.user

import java.util.*

data class User(
    val id: UserId = UserId.OwnerId(UUID.randomUUID().toString()),
    val name: UserName,
)

sealed interface UserId {
    val value: String

    data class OwnerId(override val value: String) : UserId
    data class ObserverId(override val value: String) : UserId
}

@JvmInline
value class UserName(val value: String)
