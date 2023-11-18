package com.example.boardgamesapp.screens.library

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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boardgamesapp.LibraryGamesOverviewModel
import com.example.boardgamesapp.components.CardListItem

@Composable
fun LibraryScreen(
    toDetailPage: () -> Unit,
    libraryGamesOverviewModel: LibraryGamesOverviewModel = viewModel()
) {
    val gamesOverviewState by libraryGamesOverviewModel.uiState.collectAsState()
    val lazyListState = rememberLazyListState()
    LazyColumn(
        state = lazyListState,
        modifier = Modifier.padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
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
