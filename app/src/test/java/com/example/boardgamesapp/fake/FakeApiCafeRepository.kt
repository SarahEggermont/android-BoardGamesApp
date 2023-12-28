package com.example.boardgamesapp.fake

import com.example.boardgamesapp.data.CafesRepository
import com.example.boardgamesapp.model.Cafe
import com.example.boardgamesapp.network.asDomainObjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Fake repository for testing.
 */
class FakeApiCafeRepository : CafesRepository {
    override fun getCafes(): Flow<List<Cafe>> = flow {
        emit(FakeDataSource.cafes.asDomainObjects())
    }

    override fun getCafes(search: String): Flow<List<Cafe>> = flow {
        emit(
            FakeDataSource.cafes.asDomainObjects().filter {
                it.nameNl.contains(search)
            }
        )
    }

    override fun getCafe(name: String): Flow<Cafe> = flow {
        FakeDataSource.cafes.asDomainObjects().find { it.nameNl == name }
    }

    override suspend fun refresh() {
        // not needed for fake repository
    }

    override suspend fun refreshSearch(search: String) {
        // not needed for fake repository
    }

    override suspend fun refreshOne(name: String) {
        // not needed for fake repository
    }
}
