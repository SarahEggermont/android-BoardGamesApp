package com.example.boardgamesapp.screens.favourites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.boardgamesapp.components.CardListItem
import com.example.boardgamesapp.fakeData.BoardGame

@Composable
fun FavouritesScreen(games: List<BoardGame>) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp).verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        for (game in games) {
            CardListItem(
                title = game.title,
                minPlayTime = game.minPlayTime,
                maxPlayTime = game.maxPlayTime,
                minPlayers = game.minPlayers,
                maxPlayers = game.maxPlayers,
                shortDescription = game.shortDescription,
                image = game.image
            )
        }
        for (game in games) {
            CardListItem(
                title = game.title,
                minPlayTime = game.minPlayTime,
                maxPlayTime = game.maxPlayTime,
                minPlayers = game.minPlayers,
                maxPlayers = game.maxPlayers,
                shortDescription = game.shortDescription,
                image = game.image
            )
        }
    }
}
