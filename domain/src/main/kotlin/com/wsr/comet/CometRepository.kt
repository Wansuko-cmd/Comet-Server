package com.wsr.comet

interface CometRepository {
    suspend fun getComets(limit: Int, offset: Int): List<Comet>
    suspend fun getComet(id: CometId): Comet
}