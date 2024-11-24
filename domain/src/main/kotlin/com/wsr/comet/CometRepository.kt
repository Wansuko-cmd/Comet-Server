package com.wsr.comet

import com.wsr.user.UserId

interface CometRepository {
    suspend fun getComet(id: CometId): Comet
    suspend fun getComets(limit: Int, offset: Int): List<Comet>
    suspend fun getOwnedComets(ownerId: UserId.OwnerId, limit: Int, offset: Int): List<Comet>
}