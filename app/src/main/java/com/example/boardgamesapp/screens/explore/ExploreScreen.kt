package com.example.boardgamesapp.screens.explore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.boardgamesapp.ExploreGamesOverviewModel
import com.example.boardgamesapp.components.BigCardListItem

@ExperimentalMaterial3Api
@Composable
fun ExploreScreen(
    navController: NavController,
    exploreGamesOverviewModel: ExploreGamesOverviewModel = viewModel()
) {
    val gamesOverviewState by exploreGamesOverviewModel.uiState.collectAsState()
    val lazyListState = rememberLazyListState()
    LazyColumn(
        state = lazyListState,
        modifier = Modifier.padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // TODO: searchbar
        item {
            Text(
                text = "Trending",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Start
            )
        }
        items(gamesOverviewState.currentGamesList) {
            BigCardListItem(
                title = it.title,
                minPlayTime = it.minPlayTime,
                maxPlayTime = it.maxPlayTime,
                minPlayers = it.minPlayers,
                maxPlayers = it.maxPlayers,
                shortDescription = it.shortDescription,
                thumbnail = it.thumbnail,
                image = it.image,
                navController = navController
            )
        }
    }
}
