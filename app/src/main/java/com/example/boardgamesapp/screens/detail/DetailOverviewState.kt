package com.example.boardgamesapp.screens.detail

import com.example.boardgamesapp.model.Cafe

/**
 * The state of the detail screen.
 * @property inFavourites whether the cafe is in the favourites.
 */
data class DetailState(
    val inFavourites: Boolean = false
)

/**
 * The state of the detail item.
 * @property cafe the cafe.
 */
data class DetailItemState(
    val cafe: Cafe
)

/**
 * The state of the api call.
 */
sealed interface DetailApiState {
    object Success : DetailApiState
    object NotFound : DetailApiState
    object Error : DetailApiState
    object Loading : DetailApiState
}
