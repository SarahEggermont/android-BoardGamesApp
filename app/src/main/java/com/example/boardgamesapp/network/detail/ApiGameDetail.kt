package com.example.boardgamesapp.network.detail

import com.example.boardgamesapp.model.GameDetail
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable

@Serializable
data class ParameterType(
    val type: String = "",
    val id: String = ""
)

@Serializable
data class NameTypeWrapper(
    @JsonProperty("$")
    val wrapper: NameType
)

@Serializable
data class NameType(
    val type: String = "",
    val sortindex: String = "",
    val value: String = ""
)

@Serializable
data class FieldType(
    @JsonProperty("$")
    val value: FieldValue? = null
)

@Serializable
data class FieldValue(
    val value: String = ""
)

@Serializable
data class ApiGameDetail(
    @JsonProperty("$")
    val parameters: ParameterType? = null,
    val thumbnail: String = "",
    val image: String = "",
    val name: List<NameTypeWrapper>,
    val description: String = "",
    val yearpublished: FieldType? = null,
    val minPlayers: FieldType? = null,
    val maxPlayers: FieldType? = null,
    val minPlayTime: FieldType? = null,
    val maxPlayTime: FieldType? = null,
    val minage: FieldType? = null
)

// extension function for a GameApi-List to convert is to a Domain Game List
fun ApiGameDetail.asDomainObjects(): GameDetail {
    return GameDetail(
        title = name[0].wrapper.value,
        shortDescription = description,
        thumbnail = thumbnail,
        image = image,
        minPlayers = minPlayers?.value?.value?.toInt(),
        maxPlayers = maxPlayers?.value?.value?.toInt(),
        minPlayTime = minPlayTime?.value?.value?.toInt(),
        maxPlayTime = minPlayTime?.value?.value?.toInt(),
        minAge = minage?.value?.value?.toInt(),
        year = yearpublished?.value?.value?.toInt()
    )
}
