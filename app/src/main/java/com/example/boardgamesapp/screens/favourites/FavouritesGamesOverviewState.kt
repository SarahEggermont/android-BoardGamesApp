package com.example.boardgamesapp.screens.favourites

import com.example.boardgamesapp.model.Game

data class FavouritesGamesOverviewState(
    val currentGamesList: List<Game>,
    val scrollAtActionIdx: Int = 0,
    val scrollToItemIndex: Int = 0
)

sealed interface FavouritesApiState {
    data class Success(val tasks: List<Game>) : FavouritesApiState
    object Error : FavouritesApiState
    object Loading : FavouritesApiState
}
