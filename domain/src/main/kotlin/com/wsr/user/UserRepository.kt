package com.wsr.user

interface UserRepository {
    suspend fun getUser(id: UserId): User

    suspend fun getUsers(ids: List<UserId>): List<User>

    suspend fun createUser(user: User)
}
