package com.example.boardgamesapp.screens.explore

import com.example.boardgamesapp.fakeData.BoardGame

data class ExploreGamesOverviewState(
    val currentGamesList: List<BoardGame>,
    val scrollAtActionIdx: Int = 0,
    val scrollToItemIndex: Int = 0

)
