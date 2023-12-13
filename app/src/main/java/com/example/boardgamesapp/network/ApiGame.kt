package com.example.boardgamesapp.network

import com.example.boardgamesapp.model.Game
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ParameterType(
    val id: String,
    val rank: String
)

@Serializable
data class FieldType(
    @SerialName("$")
    val upper: FieldValue
)

@Serializable
data class FieldValue(
    val value: String
)

@Serializable
class ApiGame(
    @SerialName("$")
    val parameters: ParameterType,
    val thumbnail: FieldType,
    val name: FieldType,
    val yearpublished: FieldType
)

// extension function for a GameApi-List to convert is to a Domain Game List
fun List<ApiGame>.asDomainObjects(): List<Game> {
    val domainList = this.map {
        Game(
            id = it.parameters.id,
            title = it.name.upper.value,
            thumbnail = it.thumbnail.upper.value,
            year = it.yearpublished.upper.value.toInt()
        )
    }
    return domainList
}
