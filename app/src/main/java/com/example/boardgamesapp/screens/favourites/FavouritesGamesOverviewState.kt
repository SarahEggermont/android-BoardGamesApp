package com.example.boardgamesapp.screens.favourites

import com.example.boardgamesapp.fakeData.BoardGame

data class FavouritesGamesOverviewState(
    val currentGamesList: List<BoardGame>,
    val scrollAtActionIdx: Int = 0,
    val scrollToItemIndex: Int = 0
)
