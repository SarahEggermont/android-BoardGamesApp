package com.example.boardgamesapp.screens.explore

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.example.boardgamesapp.ExploreGamesOverviewModel
import com.example.boardgamesapp.R
import com.example.boardgamesapp.components.BigCardListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(
    toDetailPage: (id: String) -> Unit,
    exploreGamesOverviewModel: ExploreGamesOverviewModel = viewModel(
        factory = ExploreGamesOverviewModel.Factory
    )
) {
    val gamesOverviewState by exploreGamesOverviewModel.uiState.collectAsState()

    val gameApiState = exploreGamesOverviewModel.gameApiState

    Box(modifier = Modifier) {
        when (gameApiState) {
            is GamesApiState.Loading -> Text(text = "Loading...")
            is GamesApiState.Error -> Text(text = "Error while loading the trending games.")
            is GamesApiState.Success -> GamesListComponent(
                gamesOverviewState = gamesOverviewState,
                exploreGamesOverviewModel = exploreGamesOverviewModel,
                toDetailPage = toDetailPage
            )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun GamesListComponent(
    gamesOverviewState: ExploreGamesOverviewState,
    exploreGamesOverviewModel: ExploreGamesOverviewModel,
    toDetailPage: (id: String) -> Unit
) {
    val lazyListState = rememberLazyListState()

    Column(
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.spacer_medium)
        )
    ) {
        SearchBar(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            query = gamesOverviewState.searchText,
            onQueryChange = {
                exploreGamesOverviewModel.setNewSearchText(it)
            },
            onSearch = {
                exploreGamesOverviewModel.searchForGames()
            },
            active = gamesOverviewState.searchActive,
            onActiveChange = {
                exploreGamesOverviewModel.setActiveSearch(it)
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.search_icon)
                )
            },
            trailingIcon = {
                if (gamesOverviewState.searchActive) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(id = R.string.close_icon),
                        modifier = Modifier.clickable {
                            if (gamesOverviewState.searchText.isNotEmpty()) {
                                exploreGamesOverviewModel.clearSearchText()
                            } else {
                                exploreGamesOverviewModel.setActiveSearch(false)
                            }
                        }
                    )
                }
            },
            placeholder = { Text(stringResource(id = R.string.searchbar_text)) }
        ) {
            gamesOverviewState.searchHistory.forEach {
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
                            exploreGamesOverviewModel.setNewSearchText(it)
                            exploreGamesOverviewModel.searchForGames()
                        }
                    )
                }
            }
        }
        LazyColumn(
            state = lazyListState,
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.spacer_small)
            )
        ) {
            item {
                Text(
                    text = stringResource(id = R.string.trending),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(id = R.dimen.padding_medium)
                    )
                )
            }
            items(gamesOverviewState.currentGamesList) {
                BigCardListItem(
                    id = it.id,
                    title = it.title,
                    thumbnail = it.thumbnail,
                    year = it.year,
                    toDetailPage = toDetailPage
                )
            }
        }
    }
}
