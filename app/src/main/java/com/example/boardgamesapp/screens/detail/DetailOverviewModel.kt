package com.example.boardgamesapp.screens.detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.boardgamesapp.GamesApplication
import com.example.boardgamesapp.data.GamesRepository
import com.example.boardgamesapp.fakeData.BoardGamesSampler
import java.io.IOException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailOverviewModel(
    savedStateHandle: SavedStateHandle,
    private val gamesRepository: GamesRepository
) : ViewModel() {
    private val _uiState =
        MutableStateFlow(
            DetailOverviewState(
                BoardGamesSampler.getOne()
            )
        )
    val uiState: StateFlow<DetailOverviewState> = _uiState.asStateFlow()

    var detailApiState: DetailApiState by mutableStateOf(DetailApiState.Loading)
        private set

    private val gameId: String = checkNotNull(savedStateHandle["gameId"])

    init {
        getApiGame(gameId)
    }

    private fun getApiGame(gameId: String) {
        viewModelScope.launch {
            try {
                val game = gamesRepository.getGame(gameId)
                _uiState.update {
                    it.copy(
                        game = game
                    )
                }
                detailApiState = DetailApiState.Success(game)
            } catch (e: IOException) {
                e.message?.let { Log.d("ExploreGamesOverviewModel", it) }
                detailApiState = DetailApiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (
                        this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                            as GamesApplication
                        )
                val gamesRepository = application.container.gamesRepository
                DetailOverviewModel(
                    this.createSavedStateHandle(),
                    gamesRepository = gamesRepository
                )
            }
        }
    }
}
