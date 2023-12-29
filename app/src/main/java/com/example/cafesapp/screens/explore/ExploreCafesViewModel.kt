package com.example.cafesapp.screens.explore

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.cafesapp.CafeApplication
import com.example.cafesapp.data.CafesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

/**
 * The view model for the explore cafes screen.
 *
 * @param cafesRepository the cafes repository.
 * @constructor loads the cafes.
 */
class ExploreCafesViewModel(private val cafesRepository: CafesRepository) : ViewModel() {
    private val _uiState =
        MutableStateFlow(
            ExploreCafesState(),
        )
    val uiState: StateFlow<ExploreCafesState> = _uiState.asStateFlow()
    lateinit var uiListState: StateFlow<ExploreCafesListState>

    // initial value is Loading
    var cafesApiState: CafesApiState by mutableStateOf(CafesApiState.Loading)
        private set

    init {
        getApiCafes()
    }

    /**
     * Searches for cafes depending on the searchText in the _uiState.
     *
     * @see _uiState
     */
    fun searchForCafes() {
        if (_uiState.value.searchText.isEmpty()) {
            getApiCafes()
        } else {
            try {
                viewModelScope.launch { cafesRepository.refreshSearch(_uiState.value.searchText) }

                uiListState =
                    cafesRepository.getCafes(_uiState.value.searchText)
                        .map { ExploreCafesListState(it) }
                        .stateIn(
                            scope = viewModelScope,
                            started = SharingStarted.WhileSubscribed(5_000L),
                            initialValue = ExploreCafesListState(),
                        )
                _uiState.update {
                    it.copy(
                        searchActive = false,
                        searchHistory = it.searchHistory + it.searchText,
                    )
                }
                cafesApiState = CafesApiState.Success
            } catch (e: IOException) {
                Log.e("ExploreCafesViewModel", "getApiCafes: ${e.message}")
                cafesApiState = CafesApiState.Error
            }
        }
    }

    /**
     * Set new search text
     *
     * @param text
     */
    fun setNewSearchText(text: String) {
        _uiState.update {
            it.copy(
                searchText = text,
            )
        }
    }

    /**
     * Clear search text
     */
    fun clearSearchText() {
        _uiState.update {
            it.copy(
                searchText = "",
            )
        }
    }

    /**
     * Set active search
     *
     * @param active
     */
    fun setActiveSearch(active: Boolean) {
        _uiState.update {
            it.copy(
                searchActive = active,
            )
        }
    }

    /**
     * Loads the cafes.
     */
    fun getApiCafes() {
        try {
            viewModelScope.launch { cafesRepository.refresh() }

            uiListState =
                cafesRepository.getCafes().map { ExploreCafesListState(it) }
                    .stateIn(
                        scope = viewModelScope,
                        started = SharingStarted.WhileSubscribed(5_000L),
                        initialValue = ExploreCafesListState(),
                    )
            if (uiListState.value.cafesList.isEmpty()) {
                cafesApiState = CafesApiState.NotFound
            }
            cafesApiState = CafesApiState.Success
        } catch (e: IOException) {
            Log.e("ExploreCafesViewModel", "getApiCafes: ${e.message}")
            cafesApiState = CafesApiState.Error
        }
    }

    /**
     * Factory for the [ExploreCafesViewModel].
     */
    companion object {
        val Factory: ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    val application =
                        (
                            this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                                as CafeApplication
                        )
                    val cafesRepository = application.container.cafesRepository
                    ExploreCafesViewModel(
                        cafesRepository = cafesRepository,
                    )
                }
            }
    }
}
