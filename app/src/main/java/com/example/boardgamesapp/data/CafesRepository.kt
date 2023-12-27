package com.example.boardgamesapp.data

import android.util.Log
import com.example.boardgamesapp.data.database.CafeDao
import com.example.boardgamesapp.data.database.asDbCafe
import com.example.boardgamesapp.data.database.asDomainCafe
import com.example.boardgamesapp.data.database.asDomainCafes
import com.example.boardgamesapp.model.Cafe
import com.example.boardgamesapp.network.CafeApiService
import com.example.boardgamesapp.network.asDomainObject
import com.example.boardgamesapp.network.asDomainObjects
import com.example.boardgamesapp.network.getCafeAsFlow
import com.example.boardgamesapp.network.getCafesAsFlow
import java.net.SocketTimeoutException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

interface CafesRepository {
    fun getCafes(): Flow<List<Cafe>>
    fun getCafe(name: String): Flow<Cafe>

    suspend fun insertTask(cafe: Cafe)
    suspend fun deleteTask(cafe: Cafe)
    suspend fun updateTask(cafe: Cafe)
    suspend fun refresh()
    suspend fun refreshOne(name: String)
}

class ApiCafesRepository(private val cafeDao: CafeDao, private val cafeApiService: CafeApiService) :
    CafesRepository {
    override fun getCafes(): Flow<List<Cafe>> {
        return cafeDao.getAllItems().map {
            it.asDomainCafes()
        }.onEach {
            if (it.isEmpty()) {
                refresh()
            }
        }
    }

    override fun getCafe(name: String): Flow<Cafe> {
        return cafeDao.getItem(name).map {
            if (it == null) {
                refreshOne(name)
            }
            it.asDomainCafe()
        }
    }

    override suspend fun insertTask(cafe: Cafe) {
        cafeDao.insert(cafe.asDbCafe())
    }

    override suspend fun deleteTask(cafe: Cafe) {
        cafeDao.delete(cafe.asDbCafe())
    }

    override suspend fun updateTask(cafe: Cafe) {
        cafeDao.update(cafe.asDbCafe())
    }

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
