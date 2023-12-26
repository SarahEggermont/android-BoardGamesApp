package com.example.boardgamesapp.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DetailScreen(
    detailOverviewModel: DetailOverviewModel = viewModel(
        factory = DetailOverviewModel.Factory
    )
) {
    val detailOverviewState by detailOverviewModel.uiState.collectAsState()

    val detailApiState = detailOverviewModel.detailApiState

    Box {
        when (detailApiState) {
            is DetailApiState.Loading -> Text(text = "Loading...")
            is DetailApiState.Error -> Text(text = "Error while loading the game.")
            is DetailApiState.Success ->
                DetailScreenList(
                    detailOverviewState = detailOverviewState,
                    detailOverviewModel = detailOverviewModel
                )

            is DetailApiState.NotFound -> {
                Text(text = "Game not found. Try again later.")
            }
        }
    }
}

@Composable
fun DetailScreenList(
    detailOverviewState: DetailOverviewState,
    detailOverviewModel: DetailOverviewModel
) {
    Box {
        Text(text = detailOverviewState.game.title)
    }
}

@Preview
@Composable
fun DetailScreenPreview() {
    DetailScreen()
}
