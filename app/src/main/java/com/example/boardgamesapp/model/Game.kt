package com.example.boardgamesapp.model

import com.example.boardgamesapp.data.database.DatabaseGame

data class Game(
    var id: String,
    var title: String,
    var thumbnail: String?,
    var year: String?,
    var isFavourite: Boolean = false,
    var inLibrary: Boolean = false
)

fun Game.asDatabaseGame(): DatabaseGame {
    return DatabaseGame(
        title = this.title,
        thumbnail = this.thumbnail,
        yearPublished = this.year,
        isFavourite = this.isFavourite,
        inLibrary = this.inLibrary
    )
}
