package com.example.boardgamesapp.screens.detail

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import coil.compose.SubcomposeAsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.boardgamesapp.R

@Composable
fun DetailScreen(
    detailOverviewModel: DetailOverviewModel = viewModel(
        factory = DetailOverviewModel.Factory
    )
) {
    val detailOverviewState by detailOverviewModel.uiState.collectAsState()
    val cafeItemState by detailOverviewModel.uiItemState.collectAsState()

    val detailApiState = detailOverviewModel.detailApiState

    Box {
        when (detailApiState) {
            is DetailApiState.Loading -> Text(text = stringResource(id = R.string.loading))
            is DetailApiState.Error -> Text(text = stringResource(id = R.string.error))
            is DetailApiState.Success ->
                DetailScreenList(
                    detailState = detailOverviewState,
                    detailItemState = cafeItemState,
                    detailOverviewModel = detailOverviewModel
                )

            is DetailApiState.NotFound -> {
                Text(text = stringResource(id = R.string.no_cafe_found))
            }
        }
    }
}

@Composable
fun DetailScreenList(
    detailState: DetailState,
    detailItemState: DetailItemState,
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
                    text = detailItemState.cafe.nameNl
                )
            }
        }
        item {
            Box(
                contentAlignment = Alignment.Center
            ) {
                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(detailItemState.cafe.icon)
                        .decoderFactory(SvgDecoder.Factory())
                        .crossfade(true)
                        .build(),
                    loading = {
                        CircularProgressIndicator()
                    },
                    error = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_broken_image),
                            contentDescription = "Error"
                        )
                    },
                    contentDescription = "${detailItemState.cafe.nameNl}.jpg",
                    modifier = Modifier
                        .width(dimensionResource(R.dimen.picture_box_width)),
                    alignment = Alignment.CenterStart,
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
//                    if (detailOverviewState.inFavourites) {
//                        Icon(
//                            Icons.Default.Check,
//                            contentDescription = stringResource(
//                                id = R.string.remove_from_favourites
//                            )
//                        )
//                    } else {
//                        Icon(
//                            Icons.Default.Add,
//                            contentDescription = stringResource(
//                                id = R.string.add_to_wishlist
//                            )
//                        )
//                    }
//                    Text(text = stringResource(id = R.string.wishlist))
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
                    text = detailItemState.cafe.descriptionNl
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
