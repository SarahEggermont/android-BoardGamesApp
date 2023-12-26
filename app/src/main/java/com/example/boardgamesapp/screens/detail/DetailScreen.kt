package com.example.boardgamesapp.screens.detail

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.boardgamesapp.R

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
    val lazyListState = rememberLazyListState()
    LazyColumn(
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.spacer_small)
        )
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    style = MaterialTheme.typography.titleLarge,
                    text = detailOverviewState.game.title + " (${detailOverviewState.game.year})"
                )
            }
        }
        item {
            Box(
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(detailOverviewState.game.image)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.loading_img),
                    error = painterResource(R.drawable.ic_broken_image),
                    contentDescription = "${detailOverviewState.game.title}.jpg",
                    modifier = Modifier.fillMaxWidth(),
                    alignment = Alignment.TopCenter,
                    contentScale = ContentScale.Crop
                )
            }
        }
        item {
            Row(
                modifier = Modifier
                    .padding(
                        start = dimensionResource(id = R.dimen.spacer_small),
                        end = dimensionResource(id = R.dimen.spacer_small)
                    )
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { /*TODO*/ }
                ) {
                    if (detailOverviewState.inFavourites) {
                        Icon(
                            Icons.Default.Check,
                            contentDescription = stringResource(
                                id = R.string.remove_from_favourites
                            )
                        )
                    } else {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = stringResource(
                                id = R.string.add_to_wishlist
                            )
                        )
                    }
                    Text(text = stringResource(id = R.string.wishlist))
                }
                Button(onClick = { /*TODO*/ }) {
                    if (detailOverviewState.inLibrary) {
                        Icon(
                            Icons.Default.Check,
                            contentDescription = stringResource(
                                id = R.string.remove_from_library

                            )
                        )
                    } else {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = stringResource(
                                id = R.string.add_to_library
                            )
                        )
                    }
                    Text(text = stringResource(id = R.string.library))
                }
            }
        }
        item {
            Column(
                modifier = Modifier
                    .padding(
                        start = dimensionResource(id = R.dimen.spacer_small),
                        end = dimensionResource(id = R.dimen.spacer_small),
                        bottom = dimensionResource(id = R.dimen.spacer_medium)
                    ),
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(id = R.dimen.spacer_small)
                )
            ) {
                TitleAndText(
                    title = R.string.description,
                    text = detailOverviewState.game.shortDescription
                )
                if (detailOverviewState.game.minPlayers != null && detailOverviewState.game.maxPlayers != null) {
                    TitleAndText(
                        title = R.string.number_of_players,
                        text = detailOverviewState.game.minPlayers + " - " + detailOverviewState.game.maxPlayers + " players"
                    )
                }
                if (detailOverviewState.game.minPlayTime != null && detailOverviewState.game.maxPlayTime != null) {
                    if (detailOverviewState.game.minPlayTime == detailOverviewState.game.maxPlayTime) {
                        TitleAndText(
                            title = R.string.playing_time,
                            text = detailOverviewState.game.minPlayTime + " minutes"
                        )
                    } else {
                        TitleAndText(
                            title = R.string.playing_time,
                            text = detailOverviewState.game.minPlayTime + " - " + detailOverviewState.game.maxPlayTime + " minutes"
                        )
                    }
                }
                TitleAndText(
                    title = R.string.minimum_age,
                    text = detailOverviewState.game.minAge + "+"
                )
            }
        }
    }
}

@Composable
fun TitleAndText(
    @StringRes title: Int,
    text: String
) {
    Text(
        style = MaterialTheme.typography.titleMedium,
        text = stringResource(id = title)
    )
    Text(text = text)
}
