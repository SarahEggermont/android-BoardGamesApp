package com.example.boardgamesapp.screens.explore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.boardgamesapp.components.BigCardListItem
import com.example.boardgamesapp.fakeData.BoardGame

@ExperimentalMaterial3Api
@Composable
fun ExploreScreen(games: List<BoardGame>, navController: NavController) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // TODO: searchbar
        Text(
            text = "Trending",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Start
        )
        for (game in games) {
            BigCardListItem(
                title = game.title,
                minPlayTime = game.minPlayTime,
                maxPlayTime = game.maxPlayTime,
                minPlayers = game.minPlayers,
                maxPlayers = game.maxPlayers,
                shortDescription = game.shortDescription,
                thumbnail = game.thumbnail,
                image = game.image,
                navController = navController
            )
        }
    }
}
