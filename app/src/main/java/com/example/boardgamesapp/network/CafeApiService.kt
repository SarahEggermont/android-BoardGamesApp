package com.example.boardgamesapp.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET
import retrofit2.http.Url

interface CafeApiService {
    @GET("cafes-gent/records?limit=50")
    suspend fun getCafes(): Wrapper

    @GET
    suspend fun getCafe(@Url url: String): Wrapper

    @GET
    suspend fun getCafesSearch(@Url url: String): Wrapper
}

// helper functions

fun CafeApiService.getCafesAsFlow(): Flow<List<ApiCafe>> = flow {
    emit(getCafes().results)
}

fun CafeApiService.getCafesSearchAsFlow(search: String): Flow<List<ApiCafe>> = flow {
    emit(
        getCafesSearch(
            "cafes-gent/records?where=name_nl%20like%20%22%25${
                search.replace(
                    " ",
                    "%20"
                )
            }%25%22&limit=20"
        ).results
    )
}

fun CafeApiService.getCafeAsFlow(name: String): Flow<ApiCafe> = flow {
    emit(
        getCafe(
            "cafes-gent/records?where=name_nl%20like%20%22${
                name.replace(
                    " ",
                    "%20"
                )
            }%22&limit=20"
        ).results[0]
    )
}
