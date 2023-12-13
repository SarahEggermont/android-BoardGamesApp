package com.example.boardgamesapp.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TermsOfUse(
    val termsofuse: String
)

@Serializable
class ApiGamesList(
    @SerialName("$")
    val parameters: TermsOfUse,
    val item: List<ApiGame>
)
