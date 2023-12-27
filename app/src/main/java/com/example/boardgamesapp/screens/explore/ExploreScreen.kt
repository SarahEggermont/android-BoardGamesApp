package com.example.boardgamesapp.screens.explore

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import com.example.boardgamesapp.R
import com.example.boardgamesapp.components.BigCardListItem

@Composable
fun ExploreScreen(
    toDetailPage: (id: Int) -> Unit,
    exploreCafesViewModel: ExploreCafesViewModel = viewModel(
        factory = ExploreCafesViewModel.Factory
    )
) {
    val cafesState by exploreCafesViewModel.uiState.collectAsState()
    val cafesListState by exploreCafesViewModel.uiListState.collectAsState()

    val cafeApiState = exploreCafesViewModel.cafesApiState

    Box(modifier = Modifier) {
        when (cafeApiState) {
            is CafesApiState.Loading -> Text(text = "Aan het laden...")
            is CafesApiState.Error -> Text(text = "Fout tijdens het laden van de Gentse cafés.")
            is CafesApiState.NotFound ->
                SearchBarWithElements(
                    cafesState = cafesState,
                    cafesListState = cafesListState,
                    exploreCafesViewModel = exploreCafesViewModel,
                    toDetailPage = toDetailPage,
                    notFound = true
                )

            is CafesApiState.Success ->
                SearchBarWithElements(
                    cafesState = cafesState,
                    cafesListState = cafesListState,
                    exploreCafesViewModel = exploreCafesViewModel,
                    toDetailPage = toDetailPage,
                    notFound = false
                )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarWithElements(
    cafesState: ExploreCafesState,
    cafesListState: ExploreCafesListState,
    exploreCafesViewModel: ExploreCafesViewModel,
    toDetailPage: (id: Int) -> Unit,
    notFound: Boolean
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.spacer_medium)
        )
    ) {
        SearchBar(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            query = cafesState.searchText,
            onQueryChange = {
                exploreCafesViewModel.setNewSearchText(it)
            },
            onSearch = {
//               TODO: exploreCafesViewModel.searchForGames()
            },
            active = cafesState.searchActive,
            onActiveChange = {
                exploreCafesViewModel.setActiveSearch(it)
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.search_icon)
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
                            }
                        }
                    )
                }
            },
            placeholder = { Text(stringResource(id = R.string.searchbar_text)) }
        ) {
            cafesState.searchHistory.forEach {
                Row(
                    modifier = Modifier.padding(
                        start = dimensionResource(id = R.dimen.padding_medium),
                        end = dimensionResource(id = R.dimen.padding_medium),
                        top = dimensionResource(id = R.dimen.padding_medium)
                    )
                ) {
                    Icon(
                        modifier = Modifier.padding(
                            end = dimensionResource(id = R.dimen.padding_medium)
                        ),
                        imageVector = Icons.Default.History,
                        contentDescription = stringResource(id = R.string.history_icon)
                    )
                    Text(
                        text = it,
                        modifier = Modifier.clickable {
                            exploreCafesViewModel.setNewSearchText(it)
//                            TODO: exploreCafesViewModel.searchForGames()
                        }
                    )
                }
            }
        }
        if (notFound) {
            Text(
                text = stringResource(id = R.string.no_games_found)
            )
        } else {
            GamesListComponent(
                cafesState = cafesState,
                cafesListState = cafesListState,
                toDetailPage = toDetailPage
            )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun GamesListComponent(
    cafesState: ExploreCafesState,
    cafesListState: ExploreCafesListState,
    toDetailPage: (id: Int) -> Unit
) {
    val lazyListState = rememberLazyListState()

    if (cafesListState.cafesList.isNotEmpty()) {
        Text(
            text = stringResource(id = R.string.trending),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(
                horizontal = dimensionResource(id = R.dimen.padding_medium)
            )
        )
    }

    LazyColumn(
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.spacer_small)
        ),
        contentPadding = PaddingValues(
            bottom = dimensionResource(id = R.dimen.padding_small)
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(cafesListState.cafesList) {
            BigCardListItem(
                id = it.id,
                title = it.nameNl,
                description = it.descriptionNl,
                category = it.catnameNl,
                thumbnail = it.icon,
                toDetailPage = toDetailPage
            )
        }
    }
}
