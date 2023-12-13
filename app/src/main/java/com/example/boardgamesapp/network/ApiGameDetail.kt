package com.example.boardgamesapp.network

import com.example.boardgamesapp.model.GameDetail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ApiGameDetail(
    @SerialName("$")
    val parameters: ParameterType,
    val thumbnail: FieldType,
    val name: FieldType,
    val yearpublished: FieldType,
    val image: String,
    val title: String,
    val description: String,
    val minPlayers: Int,
    val maxPlayers: Int,
    val minPlayTime: Int,
    val maxPlayTime: Int
)

// extension function for a GameApi-List to convert is to a Domain Game List
fun List<ApiGameDetail>.asDomainObjects(): List<GameDetail> {
    val domainList = this.map {
        GameDetail(
            title = it.name.upper.value,
//            shortDescription = it.description,
            shortDescription = "test",
            thumbnail = it.thumbnail.upper.value,
//            image = it.image,
            image = "test",
//            minPlayers = it.minPlayers,
            minPlayers = 2,
//            maxPlayers = it.maxPlayers,
            maxPlayers = 4,
//            minPlayTime = it.minPlayTime,
            minPlayTime = 30,
//            maxPlayTime = it.maxPlayTime
            maxPlayTime = 90,
            minAge = 8,
            year = 2012
        )
    }
    return domainList
}
