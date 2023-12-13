package com.example.boardgamesapp.network

import kotlinx.serialization.Serializable

@Serializable
class Wrapper(
    val items: ApiGamesList
)
