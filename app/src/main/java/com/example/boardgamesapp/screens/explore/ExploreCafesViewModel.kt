package com.example.boardgamesapp.screens.explore

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.boardgamesapp.CafeApplication
import com.example.boardgamesapp.data.CafesRepository
import java.io.IOException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExploreCafesViewModel(private val cafesRepository: CafesRepository) : ViewModel() {
    private val _uiState =
        MutableStateFlow(
            ExploreCafesState()
        )
    val uiState: StateFlow<ExploreCafesState> = _uiState.asStateFlow()
    lateinit var uiListState: StateFlow<ExploreCafesListState>

    var cafesApiState: CafesApiState by mutableStateOf(CafesApiState.Loading)
        private set

    init {
        getApiCafes()
    }

//    fun searchForGames() {
//        viewModelScope.launch {
//            try {
//                if (_uiState.value.searchText == "") {
//                    val listResult = gamesRepository.getTrendingGames()
//                    _uiState.update {
//                        it.copy(
//                            currentGamesList = listResult,
//                            searchActive = false
//                        )
//                    }
//                    gameApiState = GamesApiState.Success(listResult)
//                } else {
//                    val listResult =
//                        gamesRepository.getSearchGame(searchTerm = _uiState.value.searchText)
//                    _uiState.update {
//                        it.copy(
//                            currentGamesList = listResult,
//                            searchActive = false,
//                            searchHistory = it.searchHistory + it.searchText
//                        )
//                    }
//                }
//            } catch (e: NotFoundException) {
//                _uiState.update {
//                    it.copy(
//                        currentGamesList = emptyList(),
//                        searchActive = false,
//                        searchHistory = it.searchHistory + it.searchText
//                    )
//                }
//                gameApiState = GamesApiState.NotFound
//            } catch (e: Exception) {
//                e.message?.let { Log.d("ExploreGamesOverviewModel", it) }
//                gameApiState = GamesApiState.Error
//            }
//        }
//    }

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

    private fun getApiCafes() {
        try {
            viewModelScope.launch { cafesRepository.refresh() }

            uiListState = cafesRepository.getCafes().map { ExploreCafesListState(it) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = ExploreCafesListState()
                )
            cafesApiState = CafesApiState.Success
        } catch (e: IOException) {
            Log.e("ExploreCafesViewModel", "getApiCafes: ${e.message}")
            cafesApiState = CafesApiState.Error
        }
    }

    // object to tell the android framework how to handle the parameter of the viewmodel
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (
                        this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                            as CafeApplication
                        )
                val cafesRepository = application.container.cafesRepository
                ExploreCafesViewModel(
                    cafesRepository = cafesRepository
                )
            }
        }
    }
}
