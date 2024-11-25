package com.wsr.user

import java.util.UUID

data class User(
    val id: UserId = UserId(UUID.randomUUID().toString()),
    val name: UserName,
)

@JvmInline
value class UserId(
    val value: String,
)

@JvmInline
value class UserName(
    val value: String,
)
