package com.example.boardgamesapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController

data class BottomAppBarElements(
    val label: String,
    val icon: ImageVector
)

@Composable
fun MyBottomAppBar(navController: NavController, onProfilePage: Boolean) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf(
        BottomAppBarElements(Destinations.Library.name, Icons.Filled.List),
        BottomAppBarElements(Destinations.Favourites.name, Icons.Filled.Favorite),
        BottomAppBarElements(Destinations.Explore.name, Icons.Filled.Search)
    )
    if (onProfilePage) {
        NavigationBar {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    icon = { Icon(item.icon, contentDescription = item.label) },
                    label = { Text(item.label) },
                    selected = false,
                    onClick = {
                        navController.navigate(item.label)
                        selectedItem = index
                    }
                )
            }
        }
    } else {
        NavigationBar {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    icon = { Icon(item.icon, contentDescription = item.label) },
                    label = { Text(item.label) },
                    selected = selectedItem == index,
                    onClick = {
                        navController.navigate(item.label)
                        selectedItem = index
                    }
                )
            }
        }
    }
}
