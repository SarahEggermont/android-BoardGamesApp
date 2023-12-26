package com.example.boardgamesapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.boardgamesapp.model.Game
import com.example.boardgamesapp.model.GameDetail

@Entity(tableName = "games")
data class DatabaseGame(
    @PrimaryKey
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val thumbnail: String? = "",
    val image: String = "",
    val yearPublished: String? = "",
    val minPlayers: String? = "",
    val maxPlayers: String? = "",
    val minPlaytime: String? = "",
    val maxPlaytime: String? = "",
    val minAge: String? = "",

    val isFavourite: Boolean = false,
    val inLibrary: Boolean = false
)

fun DatabaseGame.asDomainGameDetail(): GameDetail {
    return GameDetail(
        id = this.id,
        title = this.title,
        shortDescription = this.description,
        thumbnail = this.thumbnail,
        image = this.image,
        year = this.yearPublished,
        minPlayers = this.minPlayers,
        maxPlayers = this.maxPlayers,
        minPlayTime = this.minPlaytime,
        maxPlayTime = this.maxPlaytime,
        minAge = this.minAge,
        isFavourite = this.isFavourite,
        inLibrary = this.inLibrary
    )
}

fun GameDetail.asDatabaseGame(): DatabaseGame {
    return DatabaseGame(
        id = this.id,
        title = this.title,
        description = this.shortDescription,
        thumbnail = this.thumbnail,
        image = this.image,
        yearPublished = this.year,
        minPlayers = this.minPlayers,
        maxPlayers = this.maxPlayers,
        minPlaytime = this.minPlayTime,
        maxPlaytime = this.maxPlayTime,
        minAge = this.minAge,
        isFavourite = this.isFavourite,
        inLibrary = this.inLibrary
    )
}

fun List<DatabaseGame>.asDomainGames(): List<Game> {
    val gameList = this.map {
        Game(
            id = it.id,
            title = it.title,
            thumbnail = it.thumbnail,
            year = it.yearPublished,
            isFavourite = it.isFavourite,
            inLibrary = it.inLibrary
        )
    }
    return gameList
}
