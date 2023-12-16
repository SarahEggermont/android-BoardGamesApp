package com.example.boardgamesapp.screens.explore

import com.example.boardgamesapp.model.Game

data class ExploreGamesOverviewState(
    val originalGamesList: List<Game>,
    val currentGamesList: List<Game>,
    val scrollAtActionIdx: Int = 0,
    val scrollToItemIndex: Int = 0,
    val searchText: String = "",
    val searchActive: Boolean = false,
    val searchHistory: List<String> = listOf()
)

sealed interface GamesApiState {
    data class Success(val tasks: List<Game>) : GamesApiState
    object NotFound : GamesApiState
    object Error : GamesApiState
    object Loading : GamesApiState
}
