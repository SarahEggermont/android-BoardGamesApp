package com.example.boardgamesapp.screens.favourites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boardgamesapp.FavouritesGamesOverviewModel
import com.example.boardgamesapp.R
import com.example.boardgamesapp.components.CardListItem

@Composable
fun FavouritesScreen(
    toDetailPage: () -> Unit,
    favouritesGamesOverviewModel: FavouritesGamesOverviewModel = viewModel()
) {
    val gamesOverviewState by favouritesGamesOverviewModel.uiState.collectAsState()
    val lazyListState = rememberLazyListState()
    LazyColumn(
        state = lazyListState,
        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacer_small))
    ) {
        items(gamesOverviewState.currentGamesList) {
            CardListItem(
                title = it.title,
                minPlayTime = it.minPlayTime,
                maxPlayTime = it.maxPlayTime,
                minPlayers = it.minPlayers,
                maxPlayers = it.maxPlayers,
                shortDescription = it.shortDescription,
                thumbnail = it.thumbnail,
                image = it.image,
                toDetailPage = toDetailPage
            )
        }
    }
}
