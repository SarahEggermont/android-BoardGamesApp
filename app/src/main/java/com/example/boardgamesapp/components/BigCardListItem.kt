package com.example.boardgamesapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.boardgamesapp.Destinations
import com.example.boardgamesapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BigCardListItem(
    title: String,
    minPlayTime: Int,
    maxPlayTime: Int,
    minPlayers: Int,
    maxPlayers: Int,
    shortDescription: String,
    thumbnail: String,
    image: String,
    navController: NavController
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    // marge van 16 aan elke kant -> -32 in totaal
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        onClick = { navController.navigate(Destinations.DetailGame.name) },
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = Modifier
            .width(screenWidth - 32.dp)
            .padding(0.dp)
    ) {
        Row(
            modifier = Modifier.height(IntrinsicSize.Min)
        ) {
            Box(
                modifier = Modifier
                    .width(166.dp)
                    .fillMaxHeight(),
                contentAlignment = Alignment.CenterStart
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(thumbnail)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.placeholder),
                    contentDescription = "$title.jpg",
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(166.dp),
                    alignment = Alignment.TopCenter,
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 8.dp, top = 6.dp, end = 4.dp, bottom = 6.dp)
                    .width(screenWidth)
            ) {
                Text(text = title, style = MaterialTheme.typography.titleLarge)
                if (minPlayTime == maxPlayTime) {
                    Text(
                        text = "$minPlayTime min " +
                            "($minPlayers - $maxPlayers players)",
                        style = MaterialTheme.typography.labelLarge
                    )
                } else {
                    Text(
                        text = "$minPlayTime - $maxPlayTime min " +
                            "($minPlayers - $maxPlayers players)",
                        style = MaterialTheme.typography.labelLarge
                    )
                }
                Text(text = shortDescription, style = MaterialTheme.typography.bodyMedium)
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
