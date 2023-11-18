package com.example.boardgamesapp

import androidx.lifecycle.ViewModel
import com.example.boardgamesapp.fakeData.BoardGamesSampler
import com.example.boardgamesapp.screens.explore.ExploreGamesOverviewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ExploreGamesOverviewModel : ViewModel() {
    private val _uiState =
        MutableStateFlow(ExploreGamesOverviewState(BoardGamesSampler.getAll()))
    val uiState: StateFlow<ExploreGamesOverviewState> = _uiState.asStateFlow()
}
