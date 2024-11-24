package com.wsr.user

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreateUserUseCase(
    private val userRepository: UserRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
) {
    suspend operator fun invoke(name: UserName) = withContext(dispatcher) {
        val user = User(name = name)
        userRepository.createUser(user)
    }
}
