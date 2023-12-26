package com.example.boardgamesapp.model

class GameDetail(
    var id: String,
    var title: String,
    var minPlayTime: String?,
    var maxPlayTime: String?,
    var minPlayers: String?,
    var maxPlayers: String?,
    var shortDescription: String,
    var thumbnail: String?,
    var image: String,
    var year: String?,
    var minAge: String?,
    var isFavourite: Boolean = false,
    var inLibrary: Boolean = false
)
