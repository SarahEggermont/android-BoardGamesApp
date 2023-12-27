package com.example.boardgamesapp.screens.favourites

import com.example.boardgamesapp.model.Cafe

/**
 * The state of the favourites screen.
 * @property scrollAtActionIdx the index of the action to scroll to.
 * @property scrollToItemIndex the index of the item to scroll to.
 */
data class FavouritesCafeState(
    val scrollAtActionIdx: Int = 0,
    val scrollToItemIndex: Int = 0
)

/**
 * The state of the favourites screen.
 * @property cafesList the list of cafes.
 */
data class FavouritesCafeListState(
    val cafesList: List<Cafe> = listOf()
)

/**
 * The state of the API call.
 */
sealed interface FavouritesApiState {
    object Success : FavouritesApiState
    object Error : FavouritesApiState
    object Loading : FavouritesApiState
}
