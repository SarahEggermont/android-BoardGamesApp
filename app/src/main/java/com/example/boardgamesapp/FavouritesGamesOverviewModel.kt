package com.example.boardgamesapp

import androidx.lifecycle.ViewModel
import com.example.boardgamesapp.fakeData.BoardGamesSampler
import com.example.boardgamesapp.screens.favourites.FavouritesGamesOverviewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavouritesGamesOverviewModel : ViewModel() {
    private val _uiState =
        MutableStateFlow(FavouritesGamesOverviewState(BoardGamesSampler.getAll()))
    val uiState: StateFlow<FavouritesGamesOverviewState> = _uiState.asStateFlow()
}
