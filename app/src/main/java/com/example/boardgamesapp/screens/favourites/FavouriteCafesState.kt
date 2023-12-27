package com.example.boardgamesapp.screens.favourites

import com.example.boardgamesapp.model.Cafe

data class FavouritesCafeState(
    val scrollAtActionIdx: Int = 0,
    val scrollToItemIndex: Int = 0
)

data class FavouritesCafeListState(
    val cafesList: List<Cafe> = listOf()
)

sealed interface FavouritesApiState {
    object Success : FavouritesApiState
    object Error : FavouritesApiState
    object Loading : FavouritesApiState
}
