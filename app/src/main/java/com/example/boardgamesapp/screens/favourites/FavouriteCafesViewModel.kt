package com.example.boardgamesapp.screens.favourites

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
import kotlinx.coroutines.launch

class FavouritesCafesViewModel(private val cafesRepository: CafesRepository) : ViewModel() {
    private val _uiState =
        MutableStateFlow(FavouritesCafeState())
    val uiState: StateFlow<FavouritesCafeState> = _uiState.asStateFlow()
    lateinit var uiListState: StateFlow<FavouritesCafeListState>

    var cafesApiState: FavouritesApiState by mutableStateOf(FavouritesApiState.Loading)
        private set

    init {
        getApiCafes()
    }

    private fun getApiCafes() {
        try {
            viewModelScope.launch { cafesRepository.refresh() }

            uiListState = cafesRepository.getCafes().map { FavouritesCafeListState(it) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = FavouritesCafeListState()
                )
            cafesApiState = FavouritesApiState.Success
        } catch (e: IOException) {
            Log.e("ExploreCafesViewModel", "getApiCafes: ${e.message}")
            cafesApiState = FavouritesApiState.Error
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
                FavouritesCafesViewModel(
                    cafesRepository = cafesRepository
                )
            }
        }
    }
}
