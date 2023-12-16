package com.example.boardgamesapp.network.list

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable

@Serializable
data class TermsOfUse(
    val termsofuse: String
)

@Serializable
class ApiGamesList(
    @JsonProperty("$")
    val parameters: TermsOfUse? = null,
    val item: List<ApiGame>
)
