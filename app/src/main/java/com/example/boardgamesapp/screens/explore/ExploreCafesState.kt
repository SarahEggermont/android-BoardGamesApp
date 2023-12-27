package com.example.boardgamesapp.screens.explore

import com.example.boardgamesapp.model.Cafe

/**
 * The state of the explore screen.
 * @property searchText the search text.
 * @property searchActive whether the search is active.
 * @property searchHistory the search history.
 */
data class ExploreCafesState(
    val searchText: String = "",
    val searchActive: Boolean = false,
    val searchHistory: List<String> = listOf()
)

/**
 * The state of the explore list.
 * @property cafesList the list of cafes.
 */
data class ExploreCafesListState(
    val cafesList: List<Cafe> = listOf()
)

/**
 * The state of the api call.
 */
sealed interface CafesApiState {
    object Success : CafesApiState
    object NotFound : CafesApiState
    object Error : CafesApiState
    object Loading : CafesApiState
}
