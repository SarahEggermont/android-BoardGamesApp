package com.example.boardgamesapp.screens.favourites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boardgamesapp.R
import com.example.boardgamesapp.components.BigCardListItem

@Composable
fun FavouritesScreen(
    toDetailPage: (name: String) -> Unit,
    favouriteCafesViewModel: FavouritesCafesViewModel = viewModel(
        factory = FavouritesCafesViewModel.Factory
    )
) {
    val cafeState by favouriteCafesViewModel.uiState.collectAsState()
    val cafesListState by favouriteCafesViewModel.uiListState.collectAsState()

    val cafeApiState = favouriteCafesViewModel.cafesApiState

    Box(modifier = Modifier) {
        when (cafeApiState) {
            is FavouritesApiState.Loading -> Text(text = "Aan het laden...")
            is FavouritesApiState.Error -> Text(
                text = "Fout tijdens het laden van de Gentse cafés."
            )

            is FavouritesApiState.Success -> CafesListComponent(
                cafesState = cafeState,
                favouritesViewModel = favouriteCafesViewModel,
                cafesListState = cafesListState,
                toDetailPage = toDetailPage
            )
        }
    }
}

@Composable
fun CafesListComponent(
    cafesState: FavouritesCafeState,
    cafesListState: FavouritesCafeListState,
    favouritesViewModel: FavouritesCafesViewModel,
    toDetailPage: (name: String) -> Unit

) {
    val lazyListState = rememberLazyListState()
    LazyColumn(
        state = lazyListState,
        modifier = Modifier.padding(
            horizontal = dimensionResource(id = R.dimen.padding_small)
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacer_small)),
        contentPadding = PaddingValues(bottom = dimensionResource(id = R.dimen.padding_small))
    ) {
        items(cafesListState.cafesList) {
            BigCardListItem(
                title = it.nameNl,
                description = it.descriptionNl,
                category = it.catnameNl,
                thumbnail = it.icon,
                toDetailPage = toDetailPage
            )
        }
    }
}
