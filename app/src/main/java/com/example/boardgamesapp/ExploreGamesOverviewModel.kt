package com.example.boardgamesapp

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.boardgamesapp.data.GamesRepository
import com.example.boardgamesapp.fakeData.BoardGamesSampler
import com.example.boardgamesapp.screens.explore.ExploreGamesOverviewState
import com.example.boardgamesapp.screens.explore.GamesApiState
import java.io.IOException
import java.util.Locale
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExploreGamesOverviewModel(private val gamesRepository: GamesRepository) : ViewModel() {
    private val _uiState =
        MutableStateFlow(
            ExploreGamesOverviewState(
                BoardGamesSampler.getAll(),
                BoardGamesSampler.getAll()
            )
        )
    val uiState: StateFlow<ExploreGamesOverviewState> = _uiState.asStateFlow()

    var gameApiState: GamesApiState by mutableStateOf(GamesApiState.Loading)
        private set

    init {
        getApiGames()
    }

    fun searchForGames() {
        _uiState.update { currentState ->
            currentState.copy(
                currentGamesList = currentState.originalGamesList.filter {
                    it.title.lowercase(Locale.getDefault())
                        .contains(currentState.searchText.lowercase(Locale.getDefault()))
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

    private fun getApiGames() {
        viewModelScope.launch {
            try {
                val listResult = gamesRepository.getTrendingGames()
                _uiState.update {
                    it.copy(
                        originalGamesList = listResult,
                        currentGamesList = listResult
                    )
                }
                gameApiState = GamesApiState.Success(listResult)
            } catch (e: IOException) {
                e.message?.let { Log.d("ExploreGamesOverviewModel", it) }
                gameApiState = GamesApiState.Error
            }
        }
    }

    // object to tell the android framework how to handle the parameter of the viewmodel
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (
                        this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                            as GamesApplication
                        )
                val gamesRepository = application.container.gamesRepository
                ExploreGamesOverviewModel(
                    gamesRepository = gamesRepository
                )
            }
        }
    }
}
