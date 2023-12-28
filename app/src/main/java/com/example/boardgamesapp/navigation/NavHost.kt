package com.example.boardgamesapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.boardgamesapp.Destinations
import com.example.boardgamesapp.screens.about.AboutScreen
import com.example.boardgamesapp.screens.detail.DetailScreen
import com.example.boardgamesapp.screens.explore.ExploreScreen

@Composable
fun NavHostElement(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = Destinations.EXPLORE,
        modifier = modifier
    ) {
        composable(Destinations.ABOUT) {
            AboutScreen()
        }
        composable(Destinations.EXPLORE) {
            ExploreScreen(toDetailPage = { name ->
                navController.navigate("Detail/$name")
            })
        }
        composable(Destinations.DETAIL) {
            DetailScreen()
        }
    }
}
