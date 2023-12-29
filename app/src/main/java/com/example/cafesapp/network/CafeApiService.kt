package com.example.cafesapp.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Cafe api service
 */
interface CafeApiService {

    /**
     * Get all cafes.
     * @return a [Wrapper] containing a [List] of [ApiCafe]s.
     */
    @GET("cafes-gent/records?limit=50")
    suspend fun getCafes(): Wrapper

    /**
     * Get a cafe by it's name.
     * @param url: [String] The url with the search query.
     * @return a a [Wrapper] containing a [List] of [ApiCafe]s.
     */
    @GET
    suspend fun getCafe(@Url url: String): Wrapper

    /**
     * Get all cafes that match the search query.
     * @param url: [String] The url with the search query.
     * @return a a [Wrapper] containing a [List] of [ApiCafe]s.
     */
    @GET
    suspend fun getCafesSearch(@Url url: String): Wrapper
}

/**
 * Get all cafes as a flow.
 * @return a [Flow] containing a [List] of [ApiCafe]s.
 */
fun CafeApiService.getCafesAsFlow(): Flow<List<ApiCafe>> = flow {
    emit(getCafes().results)
}

/**
 * Get all cafes that match the search query as a flow.
 * @param search: [String] The search query.
 * @return a [Flow] containing a [List] of [ApiCafe]s.
 */
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

/**
 * Get a cafe by it's name as a flow.
 * @param name: [String] The name of the cafe.
 * @return a [Flow] containing a [ApiCafe].
 */
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
