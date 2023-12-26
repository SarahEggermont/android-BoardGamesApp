package com.example.boardgamesapp.data

import android.content.res.Resources.NotFoundException
import android.util.Log
import com.example.boardgamesapp.model.Game
import com.example.boardgamesapp.model.GameDetail
import com.example.boardgamesapp.network.GameApiService
import com.example.boardgamesapp.network.detail.asDomainObjects
import com.example.boardgamesapp.network.list.asDomainObjects
import com.example.boardgamesapp.network.search.asDomainObjects

interface GamesRepository {
    suspend fun getTrendingGames(): List<Game>
    suspend fun getSearchGame(searchTerm: String): List<Game>
    suspend fun getGame(id: String): GameDetail
}

class ApiGameRepository(private val gameApiService: GameApiService) : GamesRepository {
    override suspend fun getTrendingGames(): List<Game> {
        return gameApiService.getTrendingGames(
            "?url=https://boardgamegeek.com/xmlapi2/hot?type=boardgame"
        ).items.item.asDomainObjects()
    }

    override suspend fun getSearchGame(searchTerm: String): List<Game> {
        val listApi = gameApiService.getSearchGame(
            "?url=https://boardgamegeek.com/xmlapi2/search?query=$searchTerm"
        )
        if (listApi.items.item?.isEmpty() == true) {
            Log.d("GamesRepository", "No games found")
            throw NotFoundException("No games found")
        }
        val list = listApi.items.item!!.asDomainObjects()
        var newList: List<Game> = listOf()
        try {
            // Beperkt de lijst tot 10 items om het aantal calls naar de API te beperken
            newList = list.take(10).map {
                val game = getGame(it.id)
                Game(
                    title = it.title,
                    year = it.year,
                    id = it.id,
                    thumbnail = game.thumbnail
                )
            }
        } catch (e: Error) {
            e.message?.let { Log.d("GamesRepository", it) }
        }
        return newList
    }

    override suspend fun getGame(id: String): GameDetail {
        return gameApiService.getGame(
            "?url=https://boardgamegeek.com/xmlapi2/thing?id=$id"
        ).items.item.asDomainObjects()
    }
}
