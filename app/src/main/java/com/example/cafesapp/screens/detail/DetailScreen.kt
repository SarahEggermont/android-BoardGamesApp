package com.example.cafesapp.screens.detail

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TravelExplore
import androidx.compose.material.icons.outlined.Map
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
import com.example.cafesapp.R
import com.example.cafesapp.util.openGoogleScreen

/**
 * The detail screen.
 *
 * @param detailOverviewModel the view model for the detail screen.
 */
@Composable
fun DetailScreen(
    detailOverviewModel: DetailViewModel =
        viewModel(
            factory = DetailViewModel.Factory,
        ),
) {
    val cafeItemState by detailOverviewModel.uiItemState.collectAsState()

    val detailApiState = detailOverviewModel.detailApiState

    Box {
        when (detailApiState) {
            is DetailApiState.Loading -> Text(text = stringResource(id = R.string.loading))
            is DetailApiState.Error -> Text(text = stringResource(id = R.string.error))
            is DetailApiState.Success ->
                DetailScreenList(
                    detailItemState = cafeItemState,
                )
        }
    }
}

/**
 * The details of the cafe.
 *
 * @param detailItemState the state of the detail item.
 */
@Composable
fun DetailScreenList(detailItemState: DetailItemState) {
    val lazyListState = rememberLazyListState()
    val context = LocalContext.current
    LazyColumn(
        state = lazyListState,
        verticalArrangement =
            Arrangement.spacedBy(
                dimensionResource(id = R.dimen.spacer_small),
            ),
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.spacer_medium)),
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                SubcomposeAsyncImage(
                    model =
                        ImageRequest.Builder(LocalContext.current)
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
                            contentDescription = "Error",
                        )
                    },
                    contentDescription = "${detailItemState.cafe.nameNl}.jpg",
                    modifier =
                        Modifier
                            .width(dimensionResource(R.dimen.picture_box_width)),
                    alignment = Alignment.CenterStart,
                    contentScale = ContentScale.Crop,
                )
                Column(
                    modifier = Modifier.widthIn(max = dimensionResource(id = R.dimen.button_width)),
                ) {
                    Button(
                        onClick = {
                            openGoogleScreen(detailItemState.cafe.url, context)
                        },
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Icon(
                            Icons.Default.TravelExplore,
                            contentDescription =
                                stringResource(
                                    id = R.string.website,
                                ),
                        )
                        Text(
                            text = stringResource(id = R.string.website),
                            modifier = Modifier.padding(start = dimensionResource(id = R.dimen.spacer_small)),
                        )
                    }
                    Button(
                        onClick = {
                            openGoogleScreen(
                                "https://www.google.com/maps/dir/?api=1&destination=${detailItemState.cafe.address.replace(
                                    ' ',
                                    '+',
                                )}%2C+${detailItemState.cafe.postal}+${detailItemState.cafe.local}%2C+Belgium",
                                context,
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Icon(
                            Icons.Outlined.Map,
                            contentDescription =
                                stringResource(
                                    id = R.string.navigate_to_adress,
                                ),
                        )
                        Text(
                            text = stringResource(id = R.string.navigate_to_adress),
                            modifier = Modifier.padding(start = dimensionResource(id = R.dimen.spacer_small)),
                        )
                    }
                }
            }
        }
        item {
            Column(
                modifier =
                    Modifier
                        .padding(
                            start = dimensionResource(id = R.dimen.spacer_small),
                            end = dimensionResource(id = R.dimen.spacer_small),
                            bottom = dimensionResource(id = R.dimen.spacer_medium),
                        ),
                verticalArrangement =
                    Arrangement.spacedBy(
                        dimensionResource(id = R.dimen.spacer_small),
                    ),
            ) {
                Text(
                    style = MaterialTheme.typography.bodyLarge,
                    text =
                        stringResource(id = R.string.modified) +
                            ": " +
                            detailItemState.cafe.modified.split(
                                'T',
                            )[0],
                )
                Text(
                    text = "${detailItemState.cafe.address}, ${detailItemState.cafe.postal} ${detailItemState.cafe.local}",
                    style = MaterialTheme.typography.titleMedium,
                )
                TitleAndText(
                    title = R.string.description_nl,
                    text = detailItemState.cafe.descriptionNl,
                )
                TitleAndText(
                    title = R.string.description_en,
                    text = detailItemState.cafe.descriptionEn,
                )
                TitleAndText(
                    title = R.string.description_de,
                    text = detailItemState.cafe.descriptionDe,
                )
                TitleAndText(
                    title = R.string.description_fr,
                    text = detailItemState.cafe.descriptionFr,
                )
                TitleAndText(
                    title = R.string.description_es,
                    text = detailItemState.cafe.descriptionEs,
                )
            }
        }
    }
}

/**
 * A component that show a title and it's accompanying text.
 * @param title the title.
 * @param text the text.
 */
@Composable
fun TitleAndText(
    @StringRes title: Int,
    text: String,
) {
    Text(
        style = MaterialTheme.typography.titleMedium,
        text = stringResource(id = title),
    )
    Text(
        style = MaterialTheme.typography.bodyLarge,
        text = text,
    )
}
