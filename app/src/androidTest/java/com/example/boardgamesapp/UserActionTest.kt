package com.example.boardgamesapp

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.boardgamesapp.util.CafeNavigationType
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserActionTest {

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
                navController = navController
            )
        }
    }

    /**
     * Test if the search function works.
     */
    @Test
    fun `search for cafe`() {
        // Add a delay to make sure the data is loaded
        Thread.sleep(1000)
        composeTestRule
            .onNodeWithText("Zoek in Gentse cafés")
            .performTextInput("Aba-jour\n")
        composeTestRule
            .onNodeWithText("Aba-jour")
            .assertExists()
        composeTestRule
            .onNodeWithText("Charlatan")
            .assertDoesNotExist()
    }

    /**
     * Input search text gets cleared after clicking on clear button
     */
    @Test
    fun `input search text gets cleared after clicking on clear button`() {
        Thread.sleep(1000)
        composeTestRule
            .onNodeWithText("Zoek in Gentse cafés")
            .performTextInput("Aba-jour\n")
        composeTestRule
            .onNodeWithContentDescription("Sluit icoon")
            .performClick()
        composeTestRule
            .onNodeWithText("Zoek in Gentse cafés")
            .assertExists()
    }

    /**
     * Input search text and return to overview after clearing two times
     */
    @Test
    fun `input search text and return to overview after clearing two times`() {
        Thread.sleep(1000)
        composeTestRule
            .onNodeWithText("Zoek in Gentse cafés")
            .performTextInput("Aba-jour\n")
        composeTestRule
            .onNodeWithContentDescription("Sluit icoon")
            .performClick()
        composeTestRule
            .onNodeWithContentDescription("Sluit icoon")
            .performClick()
        composeTestRule
            .onNodeWithText("Aba-jour")
            .assertExists()
    }

    /**
     * Search text gets added to history
     */
    @Test
    fun `search text gets added to history after searching`() {
        Thread.sleep(1000)
        composeTestRule
            .onNodeWithText("Zoek in Gentse cafés")
            .performTextInput("Aba-jour\n")
        composeTestRule
            .onNodeWithText("Aba-jour")
            .performClick()
        composeTestRule
            .onNodeWithContentDescription("Sluit icoon")
            .performClick()
        composeTestRule
            .onNodeWithText("Aba-jour")
            .assertExists()
    }

    /**
     * Search for cafe from search history
     */
    @Test
    fun `search for cafe from search history`() {
        Thread.sleep(1000)
        composeTestRule
            .onNodeWithText("Zoek in Gentse cafés")
            .performTextInput("Aba-jour\n")
        composeTestRule
            .onNodeWithText("Aba-jour")
            .performClick()
        composeTestRule
            .onNodeWithContentDescription("Sluit icoon")
            .performClick()
        composeTestRule
            .onNodeWithText("Aba-jour")
            .performClick()
        composeTestRule
            .onNodeWithText("Aba-jour")
            .assertExists()
        composeTestRule
            .onNodeWithText("Charlatan")
            .assertDoesNotExist()
    }

    /**
     * Go back from details to overview
     */
    @Test
    fun `go back from details to overview`() {
        // Add a delay to make sure the data is loaded
        Thread.sleep(1000)
        composeTestRule
            .onNodeWithText("Aba-jour")
            .assertExists()
        composeTestRule
            .onNodeWithText("Aba-jour")
            .performClick()
        composeTestRule
            .onNodeWithContentDescription("Ga terug")
            .assertExists()
        composeTestRule
            .onNodeWithContentDescription("Ga terug")
            .performClick()
        composeTestRule
            .onNodeWithText("Ga terug")
            .assertDoesNotExist()
    }
}
