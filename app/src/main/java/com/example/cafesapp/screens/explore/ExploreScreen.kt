package com.example.cafesapp.screens.explore

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cafesapp.R
import com.example.cafesapp.components.BigCardListItem
import com.example.cafesapp.util.CafeNavigationType

/**
 * The explore screen.
 * @param toDetailPage the navigation to the detail page of the cafe.
 * @param navigationType the navigation type.
 * @param exploreCafesViewModel the view model for the explore screen.
 */
@Composable
fun ExploreScreen(
    toDetailPage: (name: String) -> Unit,
    navigationType: CafeNavigationType,
    exploreCafesViewModel: ExploreCafesViewModel = viewModel(
        factory = ExploreCafesViewModel.Factory,
    ),
) {
    val cafesState by exploreCafesViewModel.uiState.collectAsState()
    val cafesListState by exploreCafesViewModel.uiListState.collectAsState()

    val cafeApiState = exploreCafesViewModel.cafesApiState

    Box {
        when (cafeApiState) {
            is CafesApiState.Loading -> Text(text = stringResource(id = R.string.loading))
            is CafesApiState.Error -> Text(text = stringResource(id = R.string.error))
            is CafesApiState.NotFound ->
                SearchBarWithElements(
                    cafesState = cafesState,
                    cafesListState = cafesListState,
                    exploreCafesViewModel = exploreCafesViewModel,
                    toDetailPage = toDetailPage,
                    notFound = true,
                    navigationType = navigationType,
                )

            is CafesApiState.Success ->
                SearchBarWithElements(
                    cafesState = cafesState,
                    cafesListState = cafesListState,
                    exploreCafesViewModel = exploreCafesViewModel,
                    toDetailPage = toDetailPage,
                    notFound = false,
                    navigationType = navigationType,
                )
        }
    }
}

/**
 * A column with a searchbar and card-elements.
 * @param cafesState the state of the explore screen.
 * @param cafesListState the state of the explore list.
 * @param exploreCafesViewModel the view model for the explore screen.
 * @param toDetailPage the navigation to the detail page of the cafe.
 * @param notFound whether the cafe is not found.
 * @param navigationType the navigation type.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarWithElements(
    cafesState: ExploreCafesState,
    cafesListState: ExploreCafesListState,
    exploreCafesViewModel: ExploreCafesViewModel,
    toDetailPage: (name: String) -> Unit,
    notFound: Boolean,
    navigationType: CafeNavigationType,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.spacer_medium),
        ),
    ) {
        SearchBar(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            query = cafesState.searchText,
            onQueryChange = {
                exploreCafesViewModel.setNewSearchText(it)
            },
            onSearch = {
                exploreCafesViewModel.searchForCafes()
            },
            active = cafesState.searchActive,
            onActiveChange = {
                exploreCafesViewModel.setActiveSearch(it)
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.search_icon),
                )
            },
            trailingIcon = {
                if (cafesState.searchActive) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(id = R.string.close_icon),
                        modifier = Modifier.clickable {
                            if (cafesState.searchText.isNotEmpty()) {
                                exploreCafesViewModel.clearSearchText()
                            } else {
                                exploreCafesViewModel.setActiveSearch(false)
                                exploreCafesViewModel.searchForCafes()
                            }
                        },
                    )
                }
            },
            placeholder = { Text(stringResource(id = R.string.searchbar_text)) },
        ) {
            cafesState.searchHistory.forEach {
                Row(
                    modifier = Modifier.padding(
                        start = dimensionResource(id = R.dimen.padding_medium),
                        end = dimensionResource(id = R.dimen.padding_medium),
                        top = dimensionResource(id = R.dimen.padding_medium),
                    ),
                ) {
                    Icon(
                        modifier = Modifier.padding(
                            end = dimensionResource(id = R.dimen.padding_medium),
                        ),
                        imageVector = Icons.Default.History,
                        contentDescription = stringResource(id = R.string.history_icon),
                    )
                    Text(
                        text = it,
                        modifier = Modifier.clickable {
                            exploreCafesViewModel.setNewSearchText(it)
                            exploreCafesViewModel.searchForCafes()
                        },
                    )
                }
            }
        }
        if (notFound) {
            Text(text = stringResource(id = R.string.not_found))
        }
        if (navigationType == CafeNavigationType.PERMANENT_NAVIGATION_DRAWER) {
            CafesGridComponent(
                cafesListState = cafesListState,
                toDetailPage = toDetailPage,
            )
        } else {
            CafesListComponent(
                cafesListState = cafesListState,
                toDetailPage = toDetailPage,
            )
        }
    }
}

@Composable
fun CafesGridComponent(
    cafesListState: ExploreCafesListState,
    toDetailPage: (name: String) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.spacer_small),
        ),
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.spacer_small),
        ),
    ) {
        items(cafesListState.cafesList) {
            BigCardListItem(
                title = it.nameNl,
                description = it.descriptionNl,
                category = it.catnameNl,
                thumbnail = it.icon,
                toDetailPage = toDetailPage,
            )
        }
    }
}

/**
 * A list of card-elements.
 * @param cafesListState the state of the explore list.
 * @param toDetailPage the navigation to the detail page of the cafe.
 */
@ExperimentalMaterial3Api
@Composable
fun CafesListComponent(
    cafesListState: ExploreCafesListState,
    toDetailPage: (name: String) -> Unit,
) {
    val lazyListState = rememberLazyListState()

    LazyColumn(
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.spacer_small),
        ),
        contentPadding = PaddingValues(
            bottom = dimensionResource(id = R.dimen.padding_small),
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(cafesListState.cafesList) {
            BigCardListItem(
                title = it.nameNl,
                description = it.descriptionNl,
                category = it.catnameNl,
                thumbnail = it.icon,
                toDetailPage = toDetailPage,
            )
        }
    }
}
