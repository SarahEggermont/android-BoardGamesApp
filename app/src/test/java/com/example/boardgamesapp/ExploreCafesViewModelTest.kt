package com.example.boardgamesapp

import com.example.boardgamesapp.fake.FakeApiCafeRepository
import com.example.boardgamesapp.screens.explore.ExploreCafesViewModel
import kotlinx.coroutines.test.TestDispatcher
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Tests for the [ExploreCafesViewModel].
 * @property testDispatcher the [TestDispatcher].
 */
class ExploreCafesViewModelTest {
    private val searchQuery = "Charlatan"
    private lateinit var viewModel: ExploreCafesViewModel

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Before
    fun setUp() {
        viewModel = ExploreCafesViewModel(
            cafesRepository = FakeApiCafeRepository()
        )
    }

    /**
     * Tests to see if the [ExploreCafesViewModel.setNewSearchText] method changes the state.
     */
    @Test
    fun `setting search-query changes state`() {
        viewModel.setNewSearchText(searchQuery)
        Assert.assertEquals(viewModel.uiState.value.searchText, searchQuery)
    }

    /**
     * Tests to see if the [ExploreCafesViewModel.clearSearchText] method changes the state.
     */
    @Test
    fun `clearing search-query changes state`() {
        viewModel.setNewSearchText(searchQuery)
        viewModel.clearSearchText()
        Assert.assertEquals(viewModel.uiState.value.searchText, "")
    }

    /**
     * Tests to see if the [ExploreCafesViewModel.setActiveSearch] method changes the state.
     */
    @Test
    fun `setting active search to false changes state`() {
        viewModel.setActiveSearch(false)
        Assert.assertEquals(viewModel.uiState.value.searchActive, false)
    }

    /**
     * Tests to see if the [ExploreCafesViewModel.searchForCafes] method changes the state.
     */
    @Test
    fun `searching for cafes changes state`() {
        viewModel.setNewSearchText(searchQuery)
        viewModel.searchForCafes()
        Assert.assertEquals(viewModel.uiState.value.searchHistory[0], searchQuery)
    }
}
