package com.example.boardgamesapp.model

data class Game(
    var title: String,
    var minPlayTime: Int,
    var maxPlayTime: Int,
    var minPlayers: Int,
    var maxPlayers: Int,
    var shortDescription: String,
    var thumbnail: String,
    var image: String
)
