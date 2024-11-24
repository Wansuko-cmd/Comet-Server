package com.wsr.comet

import com.wsr.user.UserId
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetOwnedCometUseCase(
    private val repository: CometRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
) {
    suspend operator fun invoke(
        ownerId: UserId.OwnerId,
        page: Int = 0,
    ): List<Comet> = withContext(dispatcher) {
        repository.getOwnedComets(
            ownerId = ownerId,
            offset = page,
        )
    }
}
