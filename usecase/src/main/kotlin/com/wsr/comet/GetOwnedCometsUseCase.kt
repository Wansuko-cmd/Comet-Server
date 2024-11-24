package com.wsr.comet

import com.wsr.user.UserId
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetOwnedCometsUseCase(
    private val repository: CometRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
) {
    suspend operator fun invoke(
        ownerId: UserId.OwnerId,
        page: Int = 0,
    ): List<Comet.Owned> = withContext(dispatcher) {
        repository.getOwnedComets(
            ownerId = ownerId,
            offset = page,
        )
    }
}
