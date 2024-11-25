package com.wsr.user

import com.wsr.ApiResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUserUseCase(
    private val userRepository: UserRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
) {
    suspend operator fun invoke(userId: UserId): ApiResult<User, GetUserError> =
        withContext(dispatcher) {
            try {
                userRepository
                    .getUser(userId)
                    .let { ApiResult.Success(it) }
            } catch (_: Exception) {
                ApiResult.Failure(GetUserError.InternalServerError)
            }
        }
}

sealed interface GetUserError {
    data object InternalServerError : GetUserError
}
