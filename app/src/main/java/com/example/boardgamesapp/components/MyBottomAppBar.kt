package com.example.boardgamesapp.components

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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.boardgamesapp.R

data class BottomAppBarElement(
    val label: String,
    val icon: ImageVector,
    val goToPage: () -> Unit
)

@Composable
fun MyBottomAppBar(
    goToFav: () -> Unit,
    goToLib: () -> Unit,
    goToExplore: () -> Unit
) {
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }
    val items = listOf(
        BottomAppBarElement(
            stringResource(id = R.string.library),
            Icons.Filled.List,
            goToLib
        ),
        BottomAppBarElement(
            stringResource(id = R.string.favorites),
            Icons.Filled.Favorite,
            goToFav
        ),
        BottomAppBarElement(
            stringResource(id = R.string.explore),
            Icons.Filled.Search,
            goToExplore
        )
    )
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = selectedItem == index,
                onClick = {
                    item.goToPage()
                    selectedItem = index
                }
            )
        }
    }
}
