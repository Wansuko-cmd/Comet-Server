package com.wsr.user

sealed interface User {
    val id: UserId
    val name: UserName

    data class Owner(
        override val id: UserId.OwnerId,
        override val name: UserName,
    ) : User

    data class Observer(
        override val id: UserId.ObserverId,
        override val name: UserName,
    ) : User
}

sealed interface UserId {
    val value: String

    data class OwnerId(override val value: String) : UserId
    data class ObserverId(override val value: String) : UserId
}

@JvmInline
value class UserName(val value: String)
