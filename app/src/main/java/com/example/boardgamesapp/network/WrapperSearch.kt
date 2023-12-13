package com.example.boardgamesapp.network

import kotlinx.serialization.Serializable

@Serializable
class WrapperSearch(
    val items: ApiSearchList
)
