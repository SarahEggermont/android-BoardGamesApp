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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomAppBarElements(
    val label: String,
    val icon: ImageVector
)

@Composable
fun MyBottomAppBar() {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf(
        BottomAppBarElements("Library", Icons.Filled.List),
        BottomAppBarElements("Favourites", Icons.Filled.Favorite),
        BottomAppBarElements("Explore", Icons.Filled.Search)
    )
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = selectedItem == index,
                onClick = { selectedItem = index }
            )
        }
    }
}
