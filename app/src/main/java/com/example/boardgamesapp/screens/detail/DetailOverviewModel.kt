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
import com.example.boardgamesapp.CafeApplication
import com.example.boardgamesapp.data.CafesRepository
import com.example.boardgamesapp.model.Cafe
import java.io.IOException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * The view model for the detail screen.
 * @param savedStateHandle the saved state handle.
 * @param cafesRepository the cafes repository.
 * @constructor loads the cafe details.
 */
class DetailOverviewModel(
    savedStateHandle: SavedStateHandle,
    private val cafesRepository: CafesRepository
) : ViewModel() {
    private val _uiState =
        MutableStateFlow(
            DetailState()
        )
    val uiState: StateFlow<DetailState> = _uiState.asStateFlow()
    lateinit var uiItemState: StateFlow<DetailItemState>

    // initial value is Loading
    var detailApiState: DetailApiState by mutableStateOf(DetailApiState.Loading)
        private set

    private val cafeName: String = checkNotNull(savedStateHandle["name"])

    init {
        getApiCafe(cafeName)
    }

    /**
     * Loads the cafe details.
     * @param cafeName the name of the cafe to load.
     */
    private fun getApiCafe(cafeName: String) {
        try {
            viewModelScope.launch { cafesRepository.refreshOne(cafeName) }
            uiItemState = cafesRepository.getCafe(cafeName).map { DetailItemState(it) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = DetailItemState(Cafe())
                )
            if (uiItemState.value.cafe.nameNl == "") {
                detailApiState = DetailApiState.NotFound
                return
            }
            detailApiState = DetailApiState.Success
        } catch (e: IOException) {
            Log.e("ExploreCafesViewModel", "getApiCafes: ${e.message}")
            detailApiState = DetailApiState.Error
        }
    }

    /**
     * Factory for the [DetailOverviewModel].
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (
                        this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                            as CafeApplication
                        )
                val cafesRepository = application.container.cafesRepository
                DetailOverviewModel(
                    this.createSavedStateHandle(),
                    cafesRepository = cafesRepository
                )
            }
        }
    }
}
