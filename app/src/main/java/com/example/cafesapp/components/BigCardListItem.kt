package com.example.cafesapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.cafesapp.R

/**
 * A card representation of a cafe.
 *
 * @param title the name of the cafe.
 * @param description the description of the cafe in Dutch.
 * @param category the category of the cafe in Dutch.
 * @param thumbnail the url of the icon of the category of the cafe.
 * @param toDetailPage the function to navigate to the detail page of the cafe.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BigCardListItem(
    title: String,
    description: String,
    category: String,
    thumbnail: String,
    toDetailPage: (name: String) -> Unit,
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    OutlinedCard(
        colors =
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface,
            ),
        onClick = { toDetailPage(title) },
        border =
            BorderStroke(
                dimensionResource(R.dimen.border_small),
                MaterialTheme.colorScheme.outlineVariant,
            ),
        modifier =
            Modifier
                .padding(
                    horizontal = dimensionResource(R.dimen.padding_medium),
                    vertical = dimensionResource(R.dimen.no_padding),
                ),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier =
                    Modifier
                        .width(dimensionResource(R.dimen.picture_width)),
            ) {
                SubcomposeAsyncImage(
                    model =
                        ImageRequest.Builder(LocalContext.current)
                            .data(thumbnail)
                            .decoderFactory(SvgDecoder.Factory())
                            .crossfade(true)
                            .build(),
                    error = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_broken_image),
                            contentDescription = stringResource(id = R.string.error_icon),
                        )
                    },
                    contentDescription = "$title.jpg",
                    modifier =
                        Modifier
                            .width(dimensionResource(R.dimen.picture_width))
                            .padding(dimensionResource(R.dimen.padding_small))
                            .align(alignment = Alignment.Center),
                )
            }
            Column(
                modifier =
                    Modifier
                        .padding(dimensionResource(R.dimen.padding_small))
                        .width(screenWidth),
            ) {
                Text(text = title, style = MaterialTheme.typography.titleLarge)
                Text(text = category, style = MaterialTheme.typography.titleMedium)
                Text(text = description, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
