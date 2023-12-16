package com.example.boardgamesapp.network.search

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable

@Serializable
data class Parameters(
    val total: String,
    val termsofuse: String
)

@Serializable
class ApiSearchList(
    @JsonProperty("$")
    val parameters: Parameters,
    val item: List<ApiSearchGame>? = emptyList()
)
