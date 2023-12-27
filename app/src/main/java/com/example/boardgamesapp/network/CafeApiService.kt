package com.example.boardgamesapp.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET
import retrofit2.http.Query

interface CafeApiService {
    @GET("cafes-gent/records")
    suspend fun getCafes(): Wrapper

    @GET("cafes-gent/records")
    suspend fun getCafe(@Query("where") objectId: String): Wrapper
}

// helper functions

fun CafeApiService.getCafesAsFlow(): Flow<List<ApiCafe>> = flow {
    emit(getCafes().results)
}
