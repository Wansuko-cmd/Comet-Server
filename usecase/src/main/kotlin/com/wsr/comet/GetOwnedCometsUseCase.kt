package com.wsr.comet

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
        ownerId: UserId.OwnerId,
        page: Int = 0,
    ): List<GetOwnedComet> = withContext(dispatcher) {
        val ownedComets = async {
            cometRepository.getOwnedComets(
                ownerId = ownerId,
                offset = page,
            )
        }
        val ownedUser = async { userRepository.getUser(ownerId) }.await()
        ownedComets.await().map { ownedComet ->
            GetOwnedComet(
                id = ownedComet.id,
                ownerUser = ownedUser,
                core = ownedComet.core,
                coma = ownedComet.coma,
                tails = ownedComet.tails,
            )
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

