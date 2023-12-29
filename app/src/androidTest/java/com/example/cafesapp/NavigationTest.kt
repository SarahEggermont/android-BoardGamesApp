package com.example.cafesapp

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.cafesapp.util.CafeNavigationType
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Tests for the navigation.
 */
class NavigationTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: TestNavHostController

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            CafesApp(
                navigationType = CafeNavigationType.BOTTOM_NAVIGATION,
                navController = navController,
            )
        }
    }

    /**
     * Verify start destination.
     */
    @Test
    fun verifyStartDestination() {
        composeTestRule
            .onNodeWithText("Ontdek trending cafés")
            .assertIsDisplayed()
    }

    /**
     * Test the navigation to the about screen.
     */
    @Test
    fun navigateToAbout() {
        composeTestRule
            .onNodeWithText("Info")
            .performClick()
        composeTestRule
            .onNodeWithText("Over deze app")
            .assertIsDisplayed()
    }

    /**
     * Test the navigation to the detail screen.
     */
    @Test
    fun clickOnCard() {
        // Add a delay to make sure the data is loaded
        Thread.sleep(1000)
        composeTestRule
            .onNodeWithText("Aba-jour")
            .performClick()
        composeTestRule
            .onNodeWithText("Beschrijving (NL)")
            .assertIsDisplayed()
    }
}
