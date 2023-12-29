package com.example.cafesapp.screens.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.EditNote
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.cafesapp.R
import com.example.cafesapp.navigation.openGoogleScreen

/**
 * The About screen of the app.
 */
@Composable
fun AboutScreen() {
    val lazyListState = rememberLazyListState()
    val context = LocalContext.current
    LazyColumn(
        state = lazyListState,
        modifier =
            Modifier.padding(
                horizontal = dimensionResource(id = R.dimen.padding_small),
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacer_small)),
        contentPadding = PaddingValues(bottom = dimensionResource(id = R.dimen.padding_small)),
    ) {
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacer_medium)),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_gent),
                    contentDescription = stringResource(id = R.string.logo_Gent),
                    modifier = Modifier.fillMaxWidth(0.3F),
                    contentScale = ContentScale.FillWidth,
                )
                Text(
                    text = stringResource(id = R.string.open_data_portal),
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacer_medium)),
                horizontalAlignment = Alignment.Start,
                modifier =
                    Modifier.padding(
                        horizontal = dimensionResource(id = R.dimen.padding_medium),
                        vertical = dimensionResource(id = R.dimen.padding_small),
                    ),
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacer_small)),
                ) {
                    Text(
                        text = stringResource(id = R.string.disclaimer),
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = stringResource(id = R.string.disclaimer_text),
                    )
                    Text(
                        text = stringResource(id = R.string.meer_informatie),
                    )
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacer_small)),
                ) {
                    Text(
                        text = stringResource(id = R.string.contact_title),
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = stringResource(id = R.string.contact),
                    )
                }
                Button(onClick = {
                    openGoogleScreen(
                        "https://forms.gle/ZQFWSSC2thHVyB6BA",
                        context,
                    )
                }) {
                    Icon(Icons.Outlined.EditNote, contentDescription = stringResource(id = R.string.contact_title))
                    Text(text = stringResource(id = R.string.form_button))
                }
            }
        }
    }
}
