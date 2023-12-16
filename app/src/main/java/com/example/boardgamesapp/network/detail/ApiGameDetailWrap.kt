package com.example.boardgamesapp.network.detail

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable

@Serializable
data class Terms(
    val termsofuse: String
)

@Serializable
class ApiGameDetailWrap(
    @JsonProperty("$")
    val termsOfUse: Terms,
    val item: ApiGameDetail
)
