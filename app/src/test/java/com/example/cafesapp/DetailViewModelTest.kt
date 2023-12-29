package com.example.cafesapp

import androidx.lifecycle.SavedStateHandle
import com.example.cafesapp.fake.FakeApiCafeRepository
import com.example.cafesapp.fake.FakeDataSource
import com.example.cafesapp.screens.detail.DetailViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Tests for the [DetailViewModel].
 * @property viewModel the [DetailViewModel].
 * @property savedStateHandle the [SavedStateHandle].
 * @property testDispatcher the [TestDispatcherRule].
 */
@OptIn(ExperimentalCoroutinesApi::class)
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val savedStateHandle =
        SavedStateHandle(mapOf("name" to FakeDataSource.cafes[0].name_nl))

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Before
    fun setUp() {
        viewModel = DetailViewModel(
            savedStateHandle = savedStateHandle,
            cafesRepository = FakeApiCafeRepository()
        )
    }

    suspend fun <T> Flow<List<T>>.flattenToElement(): T =
        flatMapConcat { it.asFlow() }.toList()[0]

    /**
     * Tests to see if the [DetailViewModel.getApiCafe] method changes the state.
     */
    @Test
    fun `getCafe changes the state`() = runTest {
        viewModel.getApiCafe("Aba-jour")
        Assert.assertEquals(
            viewModel.uiItemState.value.cafe.nameNl,
            FakeApiCafeRepository().getCafe("Aba-jour").first().nameNl
        )
    }
}
