package com.example.boardgamesapp.screens.detail

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.annotation.StringRes
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.TravelExplore
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
    val context = LocalContext.current
    LazyColumn(
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.spacer_small)
        ),
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.spacer_medium))
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
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
                Column {
                    Button(
                        onClick = { /*TODO*/ },
                        contentPadding = PaddingValues(
                            start = dimensionResource(id = R.dimen.spacer_small),
                            end = dimensionResource(id = R.dimen.spacer_small)
                        )
                    ) {
                        if (detailState.inFavourites) {
                            Icon(
                                Icons.Default.Check,
                                contentDescription = stringResource(
                                    id = R.string.remove_from_favs
                                )
                            )
                            Text(text = stringResource(id = R.string.remove_from_favs))
                        } else {
                            Icon(
                                Icons.Default.Add,
                                contentDescription = stringResource(
                                    id = R.string.add_to_favs
                                )
                            )
                            Text(text = stringResource(id = R.string.add_to_favs))
                        }
                    }
                    Button(
                        onClick = {
                            openGoogleScreen(detailItemState.cafe.url, context)
                        }
                    ) {
                        Icon(
                            Icons.Default.TravelExplore,
                            contentDescription = stringResource(
                                id = R.string.website
                            )
                        )
                        Text(text = stringResource(id = R.string.website))
                    }
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
                Text(
                    style = MaterialTheme.typography.bodySmall,
                    text = stringResource(id = R.string.modified) +
                        ": " +
                        detailItemState.cafe.modified.split(
                            'T'
                        )[0]
                )
                Text(
                    text = "${detailItemState.cafe.address}, ${detailItemState.cafe.postal} ${detailItemState.cafe.local}",
                    style = MaterialTheme.typography.labelLarge
                )
                TitleAndText(
                    title = R.string.description_nl,
                    text = detailItemState.cafe.descriptionNl
                )
                TitleAndText(
                    title = R.string.description_en,
                    text = detailItemState.cafe.descriptionEn
                )
                TitleAndText(
                    title = R.string.description_de,
                    text = detailItemState.cafe.descriptionDe
                )
                TitleAndText(
                    title = R.string.description_fr,
                    text = detailItemState.cafe.descriptionFr
                )
                TitleAndText(
                    title = R.string.description_es,
                    text = detailItemState.cafe.descriptionEs
                )
            }
        }
    }
}

fun openGoogleScreen(url: String, context: Context) {
    val customTabs = CustomTabsIntent.Builder()
        .setShowTitle(true)
        .build()
    Log.d("DetailScreen", "openGoogleScreen: $url")
    customTabs.launchUrl(
        context,
        Uri.parse(url)
    )
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
