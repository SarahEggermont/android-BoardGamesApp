package com.example.boardgamesapp.data

import com.example.boardgamesapp.network.GameApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val gamesRepository: GamesRepository
}

// container that takes care of dependencies
class DefaultAppContainer : AppContainer {

    private val baseUrl = "https://boardgamegeek.com/xmlapi2/"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(
            Json.asConverterFactory("application/json".toMediaType())
        )
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: GameApiService by lazy {
        retrofit.create(GameApiService::class.java)
    }

    override val gamesRepository: GamesRepository by lazy {
        ApiGameRepository(retrofitService)
    }
}
