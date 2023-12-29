package com.example.cafesapp.screens.detail

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
import com.example.cafesapp.CafeApplication
import com.example.cafesapp.data.CafesRepository
import com.example.cafesapp.model.Cafe
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException

/**
 * The view model for the detail screen.
 *
 * @param savedStateHandle the saved state handle.
 * @param cafesRepository the cafes repository.
 * @constructor loads the cafe details.
 */
class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val cafesRepository: CafesRepository,
) : ViewModel() {
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
     *
     * @param cafeName the name of the cafe to load.
     */
    fun getApiCafe(cafeName: String) {
        try {
            viewModelScope.launch { cafesRepository.refreshOne(cafeName) }
            uiItemState =
                cafesRepository.getCafe(cafeName).map { DetailItemState(it) }
                    .stateIn(
                        scope = viewModelScope,
                        started = SharingStarted.WhileSubscribed(5_000L),
                        initialValue = DetailItemState(Cafe()),
                    )
            detailApiState = DetailApiState.Success
        } catch (e: IOException) {
            Log.e("ExploreCafesViewModel", "getApiCafes: ${e.message}")
            detailApiState = DetailApiState.Error
        }
    }

    /**
     * Factory for the [DetailViewModel].
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
                    DetailViewModel(
                        this.createSavedStateHandle(),
                        cafesRepository = cafesRepository,
                    )
                }
            }
    }
}
