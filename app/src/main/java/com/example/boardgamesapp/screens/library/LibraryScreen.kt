package com.example.boardgamesapp.screens.library

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boardgamesapp.R
import com.example.boardgamesapp.components.CardListItem

@Composable
fun LibraryScreen(
    toDetailPage: (id: String) -> Unit,
    libraryGamesOverviewModel: LibraryGamesOverviewModel = viewModel(
        factory = LibraryGamesOverviewModel.Factory
    )
) {
    val gamesOverviewState by libraryGamesOverviewModel.uiState.collectAsState()

    val gameApiState = libraryGamesOverviewModel.gameApiState

    Box(modifier = Modifier) {
        when (gameApiState) {
            is LibraryApiState.Loading -> Text(text = "Loading...")
            is LibraryApiState.Error -> Text(text = "Error while loading your games.")
            is LibraryApiState.Success -> GamesListComponent(
                gamesOverviewState = gamesOverviewState,
                libraryGamesOverviewModel = libraryGamesOverviewModel,
                toDetailPage = toDetailPage
            )
        }
    }
}

@Composable
fun GamesListComponent(
    gamesOverviewState: LibraryGamesOverviewState,
    libraryGamesOverviewModel: LibraryGamesOverviewModel,
    toDetailPage: (id: String) -> Unit
) {
    val lazyListState = rememberLazyListState()
    LazyColumn(
        state = lazyListState,
        modifier = Modifier.padding(
            horizontal = dimensionResource(id = R.dimen.padding_small)
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacer_small)),
        contentPadding = PaddingValues(bottom = dimensionResource(id = R.dimen.padding_small))
    ) {
        items(gamesOverviewState.currentGamesList) {
            CardListItem(
                id = it.id,
                title = if (it.title == null) "" else it.title!!,
                thumbnail = if (it.thumbnail == null) "" else it.thumbnail!!,
                year = if (it.year == null) 0 else it.year!!,
                toDetailPage = toDetailPage
            )
        }
    }
}
