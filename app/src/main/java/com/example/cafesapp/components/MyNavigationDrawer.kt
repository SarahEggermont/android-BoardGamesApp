package com.example.cafesapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavDestination
import com.example.cafesapp.NavigationBarElement
import com.example.cafesapp.R

/**
 * The navigation drawer content that navigates between the different screens.
 *
 * @param selectedDestination the selected destination.
 * @param onTabPressed the function to navigate to the page.
 * @param modifier the modifier.
 */
@Composable
fun MyNavigationDrawerContent(
    selectedDestination: NavDestination?,
    onTabPressed: ((String) -> Unit),
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        for (navItem in NavigationBarElement.values()) {
            NavigationDrawerItem(
                selected = selectedDestination?.route == navItem.name,
                label = {
                    Text(
                        text = navItem.name,
                        modifier =
                            Modifier.padding(
                                horizontal = dimensionResource(R.dimen.padding_small),
                            ),
                    )
                },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.name,
                    )
                },
                colors =
                    NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.Transparent,
                    ),
                onClick = { onTabPressed(navItem.name) },
            )
        }
    }
}
