package com.wsr.comet

import com.wsr.ApiResult
import com.wsr.user.User
import com.wsr.user.UserId
import com.wsr.user.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class GetOwnedCometsUseCase(
    private val cometRepository: CometRepository,
    private val userRepository: UserRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
) {
    suspend operator fun invoke(
        ownerId: UserId,
        page: Int = 0,
    ): ApiResult<List<GetOwnedComet>, GetOwnedCometsError> =
        withContext(dispatcher) {
            try {
                val ownedComets =
                    async {
                        cometRepository.getOwnedComets(
                            ownerId = ownerId,
                            offset = page,
                        )
                    }
                val ownedUser = async { userRepository.getUser(ownerId) }.await()
                ownedComets
                    .await()
                    .map { ownedComet ->
                        GetOwnedComet(
                            id = ownedComet.id,
                            ownerUser = ownedUser,
                            core = ownedComet.core,
                            coma = ownedComet.coma,
                            tails = ownedComet.tails,
                        )
                    }.let { ApiResult.Success(it) }
            } catch (_: Exception) {
                ApiResult.Failure(GetOwnedCometsError.InternalServerError)
            }
        }
}

data class GetOwnedComet(
    val id: CometId,
    val ownerUser: User,
    val core: Core,
    val coma: Coma?,
    val tails: List<Tail>,
)

sealed interface GetOwnedCometsError {
    data object InternalServerError : GetOwnedCometsError
}
