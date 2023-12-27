package com.example.boardgamesapp.screens.explore

import com.example.boardgamesapp.model.Cafe

data class ExploreCafesState(
    val scrollAtActionIdx: Int = 0,
    val scrollToItemIndex: Int = 0,
    val searchText: String = "",
    val searchActive: Boolean = false,
    val searchHistory: List<String> = listOf()
)

data class ExploreCafesListState(
    val cafesList: List<Cafe> = listOf()
)

sealed interface CafesApiState {
    object Success : CafesApiState
    object NotFound : CafesApiState
    object Error : CafesApiState
    object Loading : CafesApiState
}
