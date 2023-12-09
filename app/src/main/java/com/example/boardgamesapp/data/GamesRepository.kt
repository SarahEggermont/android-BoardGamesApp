package com.example.boardgamesapp.data

import com.example.boardgamesapp.model.Game
import com.example.boardgamesapp.network.GameApiService
import com.example.boardgamesapp.network.asDomainObjects

interface GamesRepository {
    suspend fun getTrendingGames(): List<Game>
}

class ApiGameRepository(private val gameApiService: GameApiService) : GamesRepository {
    override suspend fun getTrendingGames(): List<Game> {
        return gameApiService.getTrendingGames().asDomainObjects()
    }
}
