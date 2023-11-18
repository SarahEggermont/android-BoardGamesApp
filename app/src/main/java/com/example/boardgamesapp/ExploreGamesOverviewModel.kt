package com.example.boardgamesapp

import androidx.lifecycle.ViewModel
import com.example.boardgamesapp.fakeData.BoardGamesSampler
import com.example.boardgamesapp.screens.explore.ExploreGamesOverviewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ExploreGamesOverviewModel : ViewModel() {
    private val _uiState =
        MutableStateFlow(
            ExploreGamesOverviewState(
                BoardGamesSampler.getAll(),
                BoardGamesSampler.getAll()
            )
        )
    val uiState: StateFlow<ExploreGamesOverviewState> = _uiState.asStateFlow()

    fun searchForGames() {
        _uiState.update { currentState ->
            currentState.copy(
                currentGamesList = currentState.originalGamesList.filter {
                    it.title.contains(currentState.searchText) || it.shortDescription.contains(
                        currentState.searchText
                    )
                },
                searchActive = false,
                searchHistory = currentState.searchHistory + currentState.searchText
            )
        }
    }

    fun setNewSearchText(text: String) {
        _uiState.update {
            it.copy(
                searchText = text
            )
        }
    }

    fun clearSearchText() {
        _uiState.update {
            it.copy(
                searchText = ""
            )
        }
    }

    fun setActiveSearch(active: Boolean) {
        _uiState.update {
            it.copy(
                searchActive = active
            )
        }
    }
}
