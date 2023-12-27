package com.example.boardgamesapp.data

import android.content.Context
import com.example.boardgamesapp.data.database.CafeDb
import com.example.boardgamesapp.network.CafeApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

/**
 * The app container.
 * This is used to provide the repositories.
 * @property cafesRepository the cafes repository.
 */
interface AppContainer {
    val cafesRepository: CafesRepository
}

/**
 * The default app container, takes care of dependencies.
 * @see AppContainer
 */
class DefaultAppContainer(private val context: Context) : AppContainer {

    /**
     * Json settings, to ignore keys in the dataset we don't need.
     * @return a json converter factory.
     */
    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val baseUrl = "https://data.stad.gent/api/explore/v2.1/catalog/datasets/"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(
            json.asConverterFactory("application/json".toMediaType())
        )
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: CafeApiService by lazy {
        retrofit.create(CafeApiService::class.java)
    }

    /**
     * Get the cafes repository.
     * @return the cafes repository.
     */
    override val cafesRepository: CafesRepository by lazy {
        ApiCafesRepository(CafeDb.getDatabase(context = context).cafeDao(), retrofitService)
    }
}
