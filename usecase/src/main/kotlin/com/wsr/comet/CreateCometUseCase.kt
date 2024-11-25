package com.wsr.comet

import com.wsr.user.UserId
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreateCometUseCase(
    private val cometRepository: CometRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
) {
    suspend operator fun invoke(
        ownerId: UserId.OwnerId,
        content: Content,
    ) = withContext(dispatcher) {
        val comet = Comet.Owned.create(
            ownerId = ownerId,
            core = Core(content = content),
        )
        cometRepository.createComet(comet)
    }
}