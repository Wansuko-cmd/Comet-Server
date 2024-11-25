package com.wsr.user

import com.wsr.ApiResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreateUserUseCase(
    private val userRepository: UserRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
) {
    suspend operator fun invoke(name: UserName): ApiResult<Unit, CreateUserError> =
        withContext(dispatcher) {
            try {
                val user = User(name = name)
                userRepository.createUser(user)
                ApiResult.Success(Unit)
            } catch (_: Exception) {
                ApiResult.Failure(CreateUserError.InternalServerError)
            }
        }
}

sealed interface CreateUserError {
    data object InternalServerError : CreateUserError
}
