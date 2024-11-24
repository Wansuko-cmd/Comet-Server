package com.wsr.comet

import com.wsr.common.LIMIT_LATE
import com.wsr.user.UserId

interface CometRepository {
    suspend fun getComet(id: CometId): Comet

    suspend fun getComets(
        offset: Int,
        limit: Int = LIMIT_LATE,
    ): List<Comet>

    suspend fun getOwnedComets(
        ownerId: UserId.OwnerId,
        offset: Int,
        limit: Int = LIMIT_LATE,
    ): List<Comet>

    suspend fun createComet(comet: Comet)
}