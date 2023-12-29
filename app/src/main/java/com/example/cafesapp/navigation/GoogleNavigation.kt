package com.example.cafesapp.navigation

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent

/**
 * Opens the google screen.
 * @param url the url to open.
 * @param context the context of the screen.
 */
fun openGoogleScreen(
    url: String,
    context: Context,
) {
    val customTabs =
        CustomTabsIntent.Builder()
            .setShowTitle(true)
            .build()
    customTabs.launchUrl(
        context,
        Uri.parse(url),
    )
}
