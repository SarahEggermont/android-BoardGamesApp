package com.example.boardgamesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.boardgamesapp.components.MyBottomAppBar
import com.example.boardgamesapp.components.MyTopBar
import com.example.boardgamesapp.fakeData.boardGames
import com.example.boardgamesapp.screens.favourites.FavouritesScreen
import com.example.boardgamesapp.screens.library.LibraryScreen
import com.example.boardgamesapp.ui.theme.BoardGamesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoardGamesAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BoardGamesApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoardGamesApp() {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            val canNavigateBack =
                currentBackStack?.destination?.route == Destinations.DetailGame.name
            MyTopBar(
                canNavigateBack = canNavigateBack,
                when (currentBackStack?.destination?.route) {
                    Destinations.Library.name -> R.string.library_title
                    Destinations.Favourites.name -> R.string.favourites_title
                    Destinations.Explore.name -> R.string.explore_title
                    Destinations.Profile.name -> R.string.profile_title
                    else -> R.string.board_games
                },
                navController
            ) {
                navController.popBackStack()
            }
        },
        bottomBar = {
            MyBottomAppBar(
                navController,
                onProfilePage = currentBackStack?.destination?.route == Destinations.Profile.name
            )
        },
        floatingActionButton = {
            var fabShown = false
            val fabImageIcon = Icons.Default.Edit
            when (currentBackStack?.destination?.route) {
                Destinations.Library.name -> {
                    fabShown = true
                }

                Destinations.Favourites.name -> {
                    fabShown = true
                }
            }
            if (fabShown) {
                FloatingActionButton(onClick = { /* TODO */ }) {
                    Icon(fabImageIcon, contentDescription = "Edit list")
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Destinations.Library.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Destinations.Library.name) {
                // TODO: vervangen door echte data
                LibraryScreen(boardGames)
            }
            composable(Destinations.Favourites.name) {
                // TODO: vervangen door echte data
                FavouritesScreen(boardGames)
            }
            composable(Destinations.Explore.name) {
                Text(text = Destinations.Explore.name)
            }
            composable(Destinations.Profile.name) {
                Text(text = Destinations.Profile.name)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BoardGamePreview() {
    BoardGamesAppTheme {
        BoardGamesApp()
    }
}
