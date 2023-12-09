package com.example.boardgamesapp.network

import com.example.boardgamesapp.model.Game
import kotlinx.serialization.Serializable

@Serializable
class ApiGame(
    val thumbnail: String,
    val image: String,
    val title: String,
    val description: String,
    val minPlayers: Int,
    val maxPlayers: Int,
    val minPlayTime: Int,
    val maxPlayTime: Int
)

// extension function for a GameApi-List to convert is to a Domain Game List
fun List<ApiGame>.asDomainObjects(): List<Game> {
    val domainList = this.map {
        Game(
            title = it.title,
            shortDescription = it.description,
            thumbnail = it.thumbnail,
            image = it.image,
            minPlayers = it.minPlayers,
            maxPlayers = it.maxPlayers,
            minPlayTime = it.minPlayTime,
            maxPlayTime = it.maxPlayTime
        )
    }
    return domainList
}
