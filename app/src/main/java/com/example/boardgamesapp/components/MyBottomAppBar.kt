package com.example.boardgamesapp.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
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

/**
 * Navigation element.
 *
 * @property label the label of the element
 * @property icon the icon of the element
 * @property goToPage the function to navigate to the page
 * @constructor Create empty Navigation element
 */
data class NavigationElement(
    val label: String,
    val icon: ImageVector,
    val goToPage: () -> Unit
)

/**
 * A bottom navigation bar that navigates between the different screens.
 *
 * @param goToAbout the function to navigate to the about page.
 * @param goToExplore the function to navigate to the explore page.
 */
@Composable
fun MyBottomAppBar(
    goToAbout: () -> Unit,
    goToExplore: () -> Unit
) {
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }
    val items = listOf(
        NavigationElement(
            stringResource(id = R.string.explore),
            Icons.Filled.Search,
            goToExplore
        ),
        NavigationElement(
            stringResource(id = R.string.about_short),
            Icons.Filled.Info,
            goToAbout
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
