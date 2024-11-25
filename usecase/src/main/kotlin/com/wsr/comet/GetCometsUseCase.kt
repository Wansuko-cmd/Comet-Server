package com.wsr.comet

import com.wsr.ApiResult
import com.wsr.user.User
import com.wsr.user.UserId
import com.wsr.user.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCometsUseCase(
    private val cometRepository: CometRepository,
    private val userRepository: UserRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
) {
    suspend operator fun invoke(
        ownerId: UserId.OwnerId,
        page: Int = 0,
    ): ApiResult<List<GetComet>, GetCometsError> =
        withContext(dispatcher) {
            try {
                val comets =
                    cometRepository.getComets(
                        ownerId = ownerId,
                        offset = page,
                    )
                val users = userRepository.getUsers(ids = comets.map { it.ownerId })
                comets
                    .map { comet ->
                        GetComet(
                            id = comet.id,
                            ownerUser = users.first { it.id == comet.ownerId },
                            core = comet.core,
                            coma = comet.coma,
                            tails = comet.tails,
                        )
                    }.let { ApiResult.Success(it) }
            } catch (_: Exception) {
                ApiResult.Failure(GetCometsError.InternalServerError)
            }
        }
}

data class GetComet(
    val id: CometId,
    val ownerUser: User,
    val core: Core,
    val coma: Coma?,
    val tails: List<Tail>,
)

sealed interface GetCometsError {
    data object InternalServerError : GetCometsError
}
