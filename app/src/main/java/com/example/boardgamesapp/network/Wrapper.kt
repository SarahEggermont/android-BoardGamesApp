package com.example.boardgamesapp.network

import kotlinx.serialization.Serializable

@Serializable
data class Wrapper(
    val total_count: Int,
    val results: List<ApiCafe>
)
