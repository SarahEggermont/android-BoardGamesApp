package com.example.boardgamesapp.screens.detail

import com.example.boardgamesapp.model.Cafe

data class DetailState(
    val inFavourites: Boolean = false,
    val inLibrary: Boolean = false
)

data class DetailItemState(
    val cafe: Cafe = null!!
)

sealed interface DetailApiState {
    object Success : DetailApiState
    object NotFound : DetailApiState
    object Error : DetailApiState
    object Loading : DetailApiState
}
