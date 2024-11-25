package com.wsr.comet

import com.wsr.user.UserId

class FakeCometRepositoryImpl : CometRepository {
    override suspend fun getComet(id: CometId): Comet {
        TODO("Not yet implemented")
    }

    override suspend fun getComets(
        ownerId: UserId.OwnerId,
        offset: Int,
        limit: Int,
    ): List<Comet> {
        TODO("Not yet implemented")
    }

    override suspend fun getOwnedComets(
        ownerId: UserId.OwnerId,
        offset: Int,
        limit: Int,
    ): List<Comet.Owned> {
        TODO("Not yet implemented")
    }

    override suspend fun createComet(comet: Comet) {
        TODO("Not yet implemented")
    }
}
