package com.example.boardgamesapp.screens.detail

import com.example.boardgamesapp.model.GameDetail

data class DetailOverviewState(
    val game: GameDetail,
    val inFavourites: Boolean = false,
    val inLibrary: Boolean = false
)

sealed interface DetailApiState {
    data class Success(val game: GameDetail) : DetailApiState
    object NotFound : DetailApiState
    object Error : DetailApiState
    object Loading : DetailApiState
}
