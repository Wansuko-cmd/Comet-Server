package com.wsr.user

class FakeUserRepositoryImpl : UserRepository {
    override suspend fun getUser(id: UserId): User {
        TODO("Not yet implemented")
    }

    override suspend fun getUsers(ids: List<UserId>): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun createUser(user: User) {
        TODO("Not yet implemented")
    }
}
