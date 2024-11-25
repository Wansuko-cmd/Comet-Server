package com.wsr.comet

import com.wsr.ApiResult
import com.wsr.user.UserId
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreateCometUseCase(
    private val cometRepository: CometRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
) {
    suspend operator fun invoke(
        ownerId: UserId,
        content: Content,
    ): ApiResult<Unit, CreateCometError> =
        withContext(dispatcher) {
            try {
                val comet =
                    Comet.Owned.create(
                        ownerId = ownerId,
                        core = Core(content = content),
                    )
                cometRepository.createComet(comet)
                ApiResult.Success(Unit)
            } catch (_: Exception) {
                ApiResult.Failure(CreateCometError.InternalServerError)
            }
        }
}

sealed interface CreateCometError {
    data object InternalServerError : CreateCometError
}
