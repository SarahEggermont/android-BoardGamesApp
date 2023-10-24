package com.example.boardgamesapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.boardgamesapp.R
import com.example.boardgamesapp.ui.theme.BoardGamesAppTheme

@Composable
fun CardListItem(
    title: String,
    minPlayTime: Int,
    maxPlayTime: Int,
    minPlayers: Int,
    maxPlayers: Int,
    shortDescription: String,
    image: String
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    // marge van 16 aan elke kant -> -32 in totaal
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = Modifier
            .width(screenWidth - 32.dp)
            .padding(0.dp)
    ) {
        Row(
            modifier = Modifier.height(IntrinsicSize.Min)
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, top = 6.dp, end = 4.dp, bottom = 6.dp)
                    .width(screenWidth - 132.dp)
            ) {
                Text(text = title, style = MaterialTheme.typography.titleSmall)
                if (minPlayTime == maxPlayTime) {
                    Text(
                        text = "$minPlayTime min " +
                            "($minPlayers - $maxPlayers players)",
                        style = MaterialTheme.typography.bodyMedium
                    )
                } else {
                    Text(
                        text = "$minPlayTime - $maxPlayTime min " +
                            "($minPlayers - $maxPlayers players)",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Text(text = shortDescription, style = MaterialTheme.typography.bodySmall)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Image(
                    painter = painterResource(id = R.drawable.thorgal),
                    contentDescription = "Image of game",
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    alignment = Alignment.CenterEnd
                )
                // TODO: replace with real image
//                AsyncImage(
//                    model = image,
//                    contentDescription = "$title.jpg",
//                    modifier = Modifier.fillMaxHeight().fillMaxWidth(),
//                    alignment = Alignment.CenterEnd
//                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardListItemPreview() {
    BoardGamesAppTheme {
        CardListItem(
            "Thorgal: The Board Game",
            90,
            120,
            2,
            4,
            "Go on an adventure in the mystic world of Norse mythology.",
            "thorgal"
        )
    }
}
