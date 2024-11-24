package com.wsr.user

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class CreateUserUseCase(
    private val userRepository: UserRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
) {
    suspend operator fun invoke(name: UserName) {
        val user = User(name = name)
        userRepository.createUser(user)
    }
}
