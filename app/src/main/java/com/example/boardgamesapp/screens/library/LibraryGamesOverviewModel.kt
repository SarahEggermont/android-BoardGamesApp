package com.example.boardgamesapp.screens.library

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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

class LibraryGamesOverviewModel(private val gamesRepository: GamesRepository) : ViewModel() {
    private val _uiState =
        MutableStateFlow(
            LibraryGamesOverviewState(
                BoardGamesSampler.getAll()
            )
        )
    val uiState: StateFlow<LibraryGamesOverviewState> = _uiState.asStateFlow()

    var gameApiState: LibraryApiState by mutableStateOf(LibraryApiState.Loading)
        private set

    init {
        getApiGames()
    }

    private fun getApiGames() {
        viewModelScope.launch {
            try {
                val listResult = gamesRepository.getTrendingGames()
                _uiState.update {
                    it.copy(
                        currentGamesList = listResult
                    )
                }
                gameApiState = LibraryApiState.Success(listResult)
            } catch (e: IOException) {
                e.message?.let { Log.d("LibraryGamesOverviewModel", it) }
                gameApiState = LibraryApiState.Error
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
                LibraryGamesOverviewModel(
                    gamesRepository = gamesRepository
                )
            }
        }
    }
}
