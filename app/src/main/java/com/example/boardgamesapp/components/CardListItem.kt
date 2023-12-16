package com.example.boardgamesapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.boardgamesapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListItem(
    id: String,
    title: String,
    thumbnail: String,
    year: Int,
    toDetailPage: (id: String) -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    OutlinedCard(
        onClick = { toDetailPage(id) },
        border = BorderStroke(
            dimensionResource(id = R.dimen.border_small),
            MaterialTheme.colorScheme.outlineVariant
        ),
        modifier = Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.padding_small))
    ) {
        Row {
            Column(
                modifier = Modifier
                    .padding(
                        start = dimensionResource(id = R.dimen.padding_medium),
                        top = dimensionResource(id = R.dimen.padding_small),
                        end = dimensionResource(id = R.dimen.padding_small),
                        bottom = dimensionResource(id = R.dimen.padding_small)
                    )
                    .width(
                        screenWidth -
                            dimensionResource(id = R.dimen.picture_box_width)
                    )
            ) {
                Text(text = title, style = MaterialTheme.typography.titleLarge)
                if (year != 0) {
                    Text(text = year.toString(), style = MaterialTheme.typography.titleMedium)
                }
            }
            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier.fillMaxHeight()
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(thumbnail)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.loading_img),
                    error = painterResource(R.drawable.ic_broken_image),
                    contentDescription = "$title.jpg",
                    modifier = Modifier
                        .width(dimensionResource(id = R.dimen.picture_small_width))
                        .fillMaxHeight(),
                    alignment = Alignment.TopCenter,
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}
