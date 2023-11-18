package com.example.boardgamesapp.screens.library

import com.example.boardgamesapp.fakeData.BoardGame

data class LibraryGamesOverviewState(
    val currentGamesList: List<BoardGame>,
    val scrollAtActionIdx: Int = 0,
    val scrollToItemIndex: Int = 0
)
