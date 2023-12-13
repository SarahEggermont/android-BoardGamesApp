package com.example.boardgamesapp.screens.library

import com.example.boardgamesapp.model.Game

data class LibraryGamesOverviewState(
    val currentGamesList: List<Game>,
    val scrollAtActionIdx: Int = 0,
    val scrollToItemIndex: Int = 0
)

sealed interface LibraryApiState {
    data class Success(val tasks: List<Game>) : LibraryApiState
    object Error : LibraryApiState
    object Loading : LibraryApiState
}
