package com.example.boardgamesapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.boardgamesapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BigCardListItem(
    id: Int,
    title: String,
    description: String,
    category: String,
    thumbnail: String,
    toDetailPage: (name: String) -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        onClick = { toDetailPage(title) },
        border = BorderStroke(
            dimensionResource(R.dimen.border_small),
            MaterialTheme.colorScheme.outlineVariant
        ),
        modifier = Modifier
            .padding(
                horizontal = dimensionResource(R.dimen.padding_medium),
                vertical = dimensionResource(R.dimen.no_padding)
            )
    ) {
        Row {
            Box(
                contentAlignment = Alignment.CenterStart
            ) {
                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(thumbnail)
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
                    contentDescription = "$title.jpg",
                    modifier = Modifier
                        .width(dimensionResource(R.dimen.picture_box_width)),
                    alignment = Alignment.CenterStart,
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_small))
                    .width(screenWidth)
            ) {
                Text(text = title, style = MaterialTheme.typography.titleLarge)
                Text(text = category, style = MaterialTheme.typography.titleMedium)
                Text(text = description, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

// @Preview
// @Composable
// fun BigCardListItem() {
//    BoardGamesAppTheme {
//        Column(
//            modifier = Modifier.padding(horizontal = 16.dp).verticalScroll(rememberScrollState()),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.spacedBy(10.dp)
//        ) {
//            BigCardListItem(
//                title = "Imperial Settlers: Empires of the North",
//                minPlayTime = 45,
//                maxPlayTime = 90,
//                minPlayers = 1,
//                maxPlayers = 4,
//                shortDescription = "Lead one of 6 asymmetric factions to build an empire and conquer new islands.", // ktlint-disable max-line-length
//                image = "https://cf.geekdo-images.com/w6HMeWkxsTJM6zC8oxwUfQ__thumb/img/g1qP3Gm2krXidqUYAJCS-xZVz0I=/fit-in/200x150/filters:strip_icc()/pic4543694.jpg" // ktlint-disable max-line-length
//            )
//            BigCardListItem(
//                title = "Thorgal: The Board Game",
//                minPlayTime = 90,
//                maxPlayTime = 120,
//                minPlayers = 2,
//                maxPlayers = 4,
//                shortDescription = "Go on adventures in the mystic world of Norse mythology.",
//                image = "https://cf.geekdo-images.com/6LmOBOKXXS8I3nX7I4hz_g__thumb/img/0hdUKkeZA4E25_VUPUXpSvvJgls=/fit-in/200x150/filters:strip_icc()/pic6724739.jpg" // ktlint-disable max-line-length
//            )
//        }
//    }
// }
