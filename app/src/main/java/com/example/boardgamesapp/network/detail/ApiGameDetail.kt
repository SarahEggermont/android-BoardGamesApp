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
    val minplayers: FieldType? = null,
    val maxplayers: FieldType? = null,
    val minplaytime: FieldType? = null,
    val maxplaytime: FieldType? = null,
    val minage: FieldType? = null
)

// extension function for a GameApi-List to convert is to a Domain Game List
fun ApiGameDetail.asDomainObjects(): GameDetail {
    var desc = description
        .replace("&#10;", "\n")
        .replace("&mdash;description from the publisher", "")
    while (desc.contains("\n\n\n")) {
        desc = desc.replace("\n\n\n", "\n\n")
    }
    return GameDetail(
        title = name[0].wrapper.value,
        shortDescription = desc,
        thumbnail = thumbnail,
        image = image,
        minPlayers = minplayers?.value?.value,
        maxPlayers = maxplayers?.value?.value,
        minPlayTime = minplaytime?.value?.value,
        maxPlayTime = minplaytime?.value?.value,
        minAge = minage?.value?.value,
        year = yearpublished?.value?.value
    )
}
