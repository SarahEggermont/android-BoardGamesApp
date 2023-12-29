package com.example.cafesapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cafesapp.Destinations
import com.example.cafesapp.screens.about.AboutScreen
import com.example.cafesapp.screens.detail.DetailScreen
import com.example.cafesapp.screens.explore.ExploreScreen
import com.example.cafesapp.util.CafeNavigationType

@Composable
fun NavHostElement(
    navController: NavHostController,
    modifier: Modifier,
    navigationType: CafeNavigationType,
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.EXPLORE,
        modifier = modifier,
    ) {
        composable(Destinations.ABOUT) {
            AboutScreen()
        }
        composable(Destinations.EXPLORE) {
            ExploreScreen(
                toDetailPage = { name ->
                    navController.navigate("Detail/$name")
                },
                navigationType = navigationType
            )
        }
        composable(Destinations.DETAIL) {
            DetailScreen()
        }
    }
}
