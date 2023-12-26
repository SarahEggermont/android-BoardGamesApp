package com.example.boardgamesapp.data

import android.content.res.Resources.NotFoundException
import android.util.Log
import com.example.boardgamesapp.data.database.GameDao
import com.example.boardgamesapp.data.database.asDatabaseGame
import com.example.boardgamesapp.data.database.asDomainGameDetail
import com.example.boardgamesapp.data.database.asDomainGames
import com.example.boardgamesapp.model.Game
import com.example.boardgamesapp.model.GameDetail
import com.example.boardgamesapp.model.asDatabaseGame
import com.example.boardgamesapp.network.GameApiService
import com.example.boardgamesapp.network.getTrendingGamesAsFlow
import com.example.boardgamesapp.network.list.asDomainObjects
import com.example.boardgamesapp.network.search.asDomainObjects
import java.net.SocketTimeoutException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

interface GamesRepository {
    fun getTrendingGames(): Flow<List<Game>>
    fun getSearchGame(searchTerm: String): Flow<List<Game>>
    fun getGameByTitle(id: String): Flow<GameDetail?>
    fun getGameById(id: String): Flow<GameDetail?>
    fun getItem(title: String): Flow<GameDetail?>
    fun getAllItems(): Flow<List<GameDetail>>

    suspend fun insertGame(game: Game)
    suspend fun deleteGame(game: Game)
    suspend fun updateGame(game: GameDetail)

    suspend fun refreshTrending()
}

class CachingApiGameRepository(
    private val gameDao: GameDao,
    private val gameApiService: GameApiService
) : GamesRepository {

    override fun getTrendingGames(): Flow<List<Game>> {
        return gameDao.getAllItems().map {
            it.asDomainGames()
        }.onEach {
            if (it.isEmpty()) {
                refreshTrending()
            }
        }
    }

    override fun getSearchGame(searchTerm: String): Flow<List<Game>> {
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

    override fun getGameById(id: String): Flow<GameDetail?> {
        return gameDao.getGameById(
            "?url=https://boardgamegeek.com/xmlapi2/thing?id=$id"
        ).map {
            it.asDomainGameDetail()
        }
    }

    override fun getItem(title: String): Flow<GameDetail?> {
        return gameDao.getItem(title).map {
            it.asDomainGameDetail()
        }
    }

    override fun getAllItems(): Flow<List<GameDetail>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertGame(game: Game) {
        gameDao.insertGame(game.asDatabaseGame())
    }

    override suspend fun deleteGame(game: Game) {
        gameDao.deleteGame(game.asDatabaseGame())
    }

    override suspend fun updateGame(game: GameDetail) {
        gameDao.updateGame(game.asDatabaseGame())
    }

    override suspend fun refreshTrending() {
        try {
            gameApiService.getTrendingGamesAsFlow(
                "?url=https://boardgamegeek.com/xmlapi2/hot?type=boardgame"
            )
                .collect { value ->
                    for (game in value.items.item.asDomainObjects()) {
                        Log.i("TEST", "refresh: $value")
                        insertGame(game)
                    }
                }
        } catch (e: SocketTimeoutException) {
            // TODO: log something
        }
    }
}
