package com.example.boardgamesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.boardgamesapp.Destinations.DETAIL
import com.example.boardgamesapp.Destinations.EXPLORE
import com.example.boardgamesapp.Destinations.FAVOURITES
import com.example.boardgamesapp.components.MyBottomAppBar
import com.example.boardgamesapp.components.MyTopBar
import com.example.boardgamesapp.screens.detail.DetailScreen
import com.example.boardgamesapp.screens.explore.ExploreScreen
import com.example.boardgamesapp.screens.favourites.FavouritesScreen
import com.example.boardgamesapp.ui.theme.CafeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CafeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CafesApp()
                }
            }
        }
    }
}

@Composable
fun CafesApp() {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            val canNavigateBack =
                currentBackStack?.destination?.route == DETAIL
            MyTopBar(
                canNavigateBack = canNavigateBack,
                when (currentBackStack?.destination?.route) {
                    FAVOURITES -> stringResource(id = R.string.favourites_title)
                    EXPLORE -> stringResource(id = R.string.explore_title)
                    DETAIL -> currentBackStack?.arguments?.getString("name")
                        ?: stringResource(id = R.string.detail_game_title)

                    else -> stringResource(id = R.string.cafes_title)
                }
            ) {
                navController.popBackStack()
            }
        },
        bottomBar = {
            MyBottomAppBar(
                goToFav = { navController.navigate(FAVOURITES) },
                goToExplore = { navController.navigate(EXPLORE) }
            )
        },
        floatingActionButton = {
            var fabShown = false
            val fabImageIcon = Icons.Default.Edit
            when (currentBackStack?.destination?.route) {
                FAVOURITES -> {
                    fabShown = true
                }
            }
            if (fabShown) {
                FloatingActionButton(onClick = { /* TODO */ }) {
                    Icon(fabImageIcon, contentDescription = stringResource(id = R.string.edit_list))
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = EXPLORE,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(FAVOURITES) {
                FavouritesScreen(toDetailPage = { name ->
                    navController.navigate("Detail/$name")
                })
            }
            composable(EXPLORE) {
                ExploreScreen(toDetailPage = { name ->
                    navController.navigate("Detail/$name")
                })
            }
            composable(DETAIL) {
                DetailScreen()
            }
        }
    }
}
