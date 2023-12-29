package com.example.cafesapp.data

import android.util.Log
import com.example.cafesapp.data.database.CafeDao
import com.example.cafesapp.data.database.asDbCafe
import com.example.cafesapp.data.database.asDomainCafe
import com.example.cafesapp.data.database.asDomainCafes
import com.example.cafesapp.model.Cafe
import com.example.cafesapp.network.CafeApiService
import com.example.cafesapp.network.asDomainObject
import com.example.cafesapp.network.asDomainObjects
import com.example.cafesapp.network.getCafeAsFlow
import com.example.cafesapp.network.getCafesAsFlow
import com.example.cafesapp.network.getCafesSearchAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.net.SocketTimeoutException

/**
 * Repository for the cafes.
 */
interface CafesRepository {
    /**
     * Get all cafes.
     *
     * @return a [Flow] containing a [List] of [Cafe]s.
     */
    fun getCafes(): Flow<List<Cafe>>

    /**
     * Get all cafes that match the search query.
     *
     * @param search: [String] The search query.
     * @return a [Flow] containing a [List] of [Cafe]s.
     */
    fun getCafes(search: String): Flow<List<Cafe>>

    /**
     * Get a cafe by it's name.
     *
     * @param name: [String] The name of the cafe.
     * @return a [Flow] containing a [Cafe].
     */
    fun getCafe(name: String): Flow<Cafe>

    /**
     * Refresh the cafes.
     */
    suspend fun refresh()

    /**
     * Refresh the cafes that match the search query.
     *
     * @param search: [String] The search query.
     */
    suspend fun refreshSearch(search: String)

    /**
     * Refresh a cafe by it's name.
     *
     * @param name: [String] The name of the cafe.
     */
    suspend fun refreshOne(name: String)
}

/**
 * Implementation of [CafesRepository] that uses the Gentse cafés API.
 *
 * @param cafeDao the [CafeDao] instance to use.
 * @param cafeApiService the [CafeApiService] instance to use.
 */
class ApiCafesRepository(private val cafeDao: CafeDao, private val cafeApiService: CafeApiService) :
    CafesRepository {
    /**
     * Get all cafes.
     * Tries to get the cafes from the database first,
     * if that fails, tries to get them from the API and stores them locally.
     *
     * @return a [Flow] containing a [List] of [Cafe]s.
     */
    override fun getCafes(): Flow<List<Cafe>> {
        return cafeDao.getAllItems().map {
            it.asDomainCafes()
        }.onEach {
            if (it.isEmpty()) {
                refresh()
            }
        }
    }

    /**
     * Get all cafes that match the search query.
     * Tries to get the cafes from the database first,
     * if that fails, tries to get them from the API and stores them locally.
     *
     * @param search: [String] The search query.
     * @return a [Flow] containing a [List] of [Cafe]s.
     */
    override fun getCafes(search: String): Flow<List<Cafe>> {
        return cafeDao.getAllItems("%$search%").map {
            it.asDomainCafes()
        }.onEach {
            if (it.isEmpty()) {
                refreshSearch(search)
            }
        }
    }

    /**
     * Get a cafe by it's name.
     * Tries to get the cafe from the database first,
     * if that fails, tries to get it from the API and stores it locally.
     *
     * @param name: [String] The name of the cafe.
     * @return a [Flow] containing a [Cafe].
     */
    override fun getCafe(name: String): Flow<Cafe> {
        return cafeDao.getItem(name).map {
            if (it.name_nl == "") {
                refreshOne(name)
            }
            it.asDomainCafe()
        }
    }

    /**
     * Refresh the cafes.
     * Tries to get the cafes from the API and stores them locally.
     */
    override suspend fun refresh() {
        try {
            cafeApiService.getCafesAsFlow().asDomainObjects().collect { value ->
                for (cafe in value) {
                    cafeDao.insert(cafe.asDbCafe())
                }
            }
        } catch (e: SocketTimeoutException) {
            Log.e("ApiCafesRepository", "refresh: ${e.message}")
        }
    }

    /**
     * Refresh the cafes that match the search query.
     * Tries to get the cafes from the API and stores them locally.
     *
     * @param search: [String] The search query.
     */
    override suspend fun refreshSearch(search: String) {
        try {
            cafeApiService.getCafesSearchAsFlow(search).asDomainObjects().collect { value ->
                for (cafe in value) {
                    cafeDao.insert(cafe.asDbCafe())
                }
            }
        } catch (e: SocketTimeoutException) {
            Log.e("ApiCafesRepository", "refresh: ${e.message}")
        }
    }

    /**
     * Refresh a cafe by it's name.
     * Tries to get the cafe from the API and stores it locally.
     *
     * @param name: [String] The name of the cafe.
     */
    override suspend fun refreshOne(name: String) {
        try {
            cafeApiService.getCafeAsFlow(name).asDomainObject().collect { cafe ->
                cafeDao.insert(cafe.asDbCafe())
            }
        } catch (e: SocketTimeoutException) {
            Log.e("ApiCafesRepository", "refreshOne: ${e.message}")
        }
    }
}
