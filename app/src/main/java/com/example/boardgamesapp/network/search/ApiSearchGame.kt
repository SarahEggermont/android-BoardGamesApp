package com.example.boardgamesapp.network.search

import com.example.boardgamesapp.model.GameSearch
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable

@Serializable
data class ParameterType(
    val type: String = "",
    val id: String = ""
)

@Serializable
data class FieldType(
    @JsonProperty("$")
    val upper: FieldValue? = null
)

@Serializable
data class FieldValue(
    val value: String? = ""
)

@Serializable
data class NameType(
    @JsonProperty("$")
    val upper: NameValue? = null
)

@Serializable
data class NameValue(
    val type: String = "",
    val value: String = ""
)

@Serializable
class ApiSearchGame(
    @JsonProperty("$")
    val parameters: ParameterType,
    val name: NameType?,
    val yearpublished: FieldType? = null
)

// extension function for a GameApi-List to convert is to a Domain Game List
fun List<ApiSearchGame>.asDomainObjects(): List<GameSearch> {
    if (this.isEmpty()) {
        return emptyList()
    }
    val domainList = this.map {
        GameSearch(
            id = it.parameters.id,
            title = it.name?.upper?.value,
            year = it.yearpublished?.upper?.value?.toInt()
        )
    }
    return domainList
}
