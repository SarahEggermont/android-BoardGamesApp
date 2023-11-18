package com.example.boardgamesapp

import androidx.lifecycle.ViewModel
import com.example.boardgamesapp.fakeData.BoardGamesSampler
import com.example.boardgamesapp.screens.library.LibraryGamesOverviewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LibraryGamesOverviewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LibraryGamesOverviewState(BoardGamesSampler.getAll()))
    val uiState: StateFlow<LibraryGamesOverviewState> = _uiState.asStateFlow()
}
