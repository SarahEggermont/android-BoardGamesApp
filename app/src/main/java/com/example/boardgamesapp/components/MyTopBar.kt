package com.example.boardgamesapp.components

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.boardgamesapp.Destinations
import com.example.boardgamesapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(
    canNavigateBack: Boolean,
    @StringRes title: Int,
    navController: NavController,
    goBack: () -> Unit
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface
        ),
        title = { Text(stringResource(title)) },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = goBack) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Go back")
                }
            } else {
                // TODO: own app icon
                val image = painterResource(R.drawable.placeholder)
//                Image(
//                    painter = image,
//                    contentDescription = "App icon",
//                    modifier = Modifier.height(100.dp)
//                )
            }
        },
        actions = {
            IconButton(onClick = { navController.navigate(Destinations.Profile.name) }) {
                Icon(Icons.Filled.AccountCircle, contentDescription = "Go to profile")
            }
        }
    )
}
