package com.example.cafesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cafesapp.components.MyBottomAppBar
import com.example.cafesapp.components.MyNavigationDrawerContent
import com.example.cafesapp.components.MyNavigationRail
import com.example.cafesapp.components.MyTopBar
import com.example.cafesapp.navigation.NavHostElement
import com.example.cafesapp.util.CafeNavigationType
import com.example.compose.CafeAppTheme

/**
 * The main activity of the app.
 */
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class MainActivity : ComponentActivity() {
    /**
     * The onCreate method of the activity.
     * @param savedInstanceState the saved instance state.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CafeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val windowSize = calculateWindowSizeClass(activity = this)
                    when (windowSize.widthSizeClass) {
                        WindowWidthSizeClass.Compact -> {
                            CafesApp(CafeNavigationType.BOTTOM_NAVIGATION)
                        }

                        WindowWidthSizeClass.Medium -> {
                            CafesApp(CafeNavigationType.NAVIGATION_RAIL)
                        }

                        WindowWidthSizeClass.Expanded -> {
                            CafesApp(CafeNavigationType.PERMANENT_NAVIGATION_DRAWER)
                        }

                        else -> {
                            CafesApp(CafeNavigationType.BOTTOM_NAVIGATION)
                        }
                    }
                }
            }
        }
    }
}

/**
 * The main app with the navigation.
 */
@Composable
fun CafesApp(
    navigationType: CafeNavigationType,
    navController: NavHostController = rememberNavController(),
) {
    val currentBackStack by navController.currentBackStackEntryAsState()
    when (navigationType) {
        CafeNavigationType.BOTTOM_NAVIGATION -> {
            BottomNavigationCafesApp(navigationType, navController, currentBackStack)
        }

        CafeNavigationType.NAVIGATION_RAIL -> {
            NavigationRailCafesApp(navigationType, navController, currentBackStack)
        }

        CafeNavigationType.PERMANENT_NAVIGATION_DRAWER -> {
            PermanentNavigationDrawerCafesApp(navigationType, navController, currentBackStack)
        }
    }
}

@Composable
fun PermanentNavigationDrawerCafesApp(
    navigationType: CafeNavigationType,
    navController: NavHostController,
    currentBackStack: NavBackStackEntry?,
) {
    PermanentNavigationDrawer(drawerContent = {
        PermanentDrawerSheet(Modifier.width(dimensionResource(R.dimen.drawer_width))) {
            MyNavigationDrawerContent(
                selectedDestination = navController.currentDestination,
                onTabPressed = { node: String -> navController.navigate(node) },
                modifier =
                    Modifier
                        .wrapContentWidth()
                        .fillMaxHeight()
                        .background(MaterialTheme.colorScheme.inverseOnSurface)
                        .padding(dimensionResource(R.dimen.drawer_padding_content)),
            )
        }
    }) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                val canNavigateBack =
                    currentBackStack?.destination?.route == Destinations.DETAIL
                MyTopBar(
                    canNavigateBack = canNavigateBack,
                    when (currentBackStack?.destination?.route) {
                        Destinations.ABOUT -> stringResource(id = R.string.about)
                        Destinations.EXPLORE -> stringResource(id = R.string.explore_title)
                        Destinations.DETAIL ->
                            currentBackStack.arguments?.getString("name")
                                ?: stringResource(id = R.string.detail_game_title)

                        else -> stringResource(id = R.string.cafes_title)
                    },
                ) {
                    navController.popBackStack()
                }
            },
        ) { innerPadding ->
            NavHostElement(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
                navigationType = navigationType,
            )
        }
    }
}

@Composable
fun NavigationRailCafesApp(
    navigationType: CafeNavigationType,
    navController: NavHostController,
    currentBackStack: NavBackStackEntry?,
) {
    Row {
        AnimatedVisibility(visible = navigationType == CafeNavigationType.NAVIGATION_RAIL) {
            stringResource(R.string.navigation_rail)
            MyNavigationRail(
                selectedDestination = navController.currentDestination,
                onTabPressed = { node: String -> navController.navigate(node) },
            )
        }
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                val canNavigateBack =
                    currentBackStack?.destination?.route == Destinations.DETAIL
                MyTopBar(
                    canNavigateBack = canNavigateBack,
                    when (currentBackStack?.destination?.route) {
                        Destinations.ABOUT -> stringResource(id = R.string.about)
                        Destinations.EXPLORE -> stringResource(id = R.string.explore_title)
                        Destinations.DETAIL ->
                            currentBackStack.arguments?.getString("name")
                                ?: stringResource(id = R.string.detail_game_title)

                        else -> stringResource(id = R.string.cafes_title)
                    },
                ) {
                    navController.popBackStack()
                }
            },
        ) { innerPadding ->
            NavHostElement(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
                navigationType = navigationType,
            )
        }
    }
}

@Composable
fun BottomNavigationCafesApp(
    navigationType: CafeNavigationType,
    navController: NavHostController,
    currentBackStack: NavBackStackEntry?,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            val canNavigateBack =
                currentBackStack?.destination?.route == Destinations.DETAIL
            MyTopBar(
                canNavigateBack = canNavigateBack,
                when (currentBackStack?.destination?.route) {
                    Destinations.ABOUT -> stringResource(id = R.string.about)
                    Destinations.EXPLORE -> stringResource(id = R.string.explore_title)
                    Destinations.DETAIL ->
                        currentBackStack.arguments?.getString("name")
                            ?: stringResource(id = R.string.detail_game_title)

                    else -> stringResource(id = R.string.cafes_title)
                },
            ) {
                navController.popBackStack()
            }
        },
        bottomBar = {
            MyBottomAppBar(
                goToAbout = { navController.navigate(Destinations.ABOUT) },
                goToExplore = { navController.navigate(Destinations.EXPLORE) },
            )
        },
    ) { innerPadding ->
        NavHostElement(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            navigationType = navigationType,
        )
    }
}
