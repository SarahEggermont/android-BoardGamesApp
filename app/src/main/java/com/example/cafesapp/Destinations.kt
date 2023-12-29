package com.example.cafesapp

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * The destinations of the application.
 *
 * @property ABOUT the about route.
 * @property EXPLORE the explore route.
 * @property DETAIL the detail route.
 */
object Destinations {
    const val ABOUT = "About"
    const val EXPLORE = "Explore"
    const val DETAIL = "Detail/{name}"
}

/**
 * Navigation bar element.
 *
 * @property title the label of the element
 * @property icon the icon of the element
 * @constructor Create empty Bottom app bar element
 */
enum class NavigationBarElement(
    @StringRes val title: Int,
    val icon: ImageVector,
) {
    Explore(R.string.explore, Icons.Filled.Search),
    About(R.string.about_short, Icons.Filled.Info),
}
