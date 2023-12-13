package com.example.boardgamesapp.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Parameters(
    val total: String,
    val termsofuse: String
)

@Serializable
class ApiSearchList(
    @SerialName("$")
    val parameters: Parameters,
    val item: List<ApiGame>
)
