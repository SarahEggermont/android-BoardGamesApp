package com.example.boardgamesapp.data

import com.example.boardgamesapp.network.GameApiService
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

interface AppContainer {
    val gamesRepository: GamesRepository
}

// container that takes care of dependencies
class DefaultAppContainer : AppContainer {

    private val mapper =
        ObjectMapper().registerModule(KotlinModule())
            .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)

    private val baseUrl =
        "https://v1.nocodeapi.com/boardgame_api/xml_to_json/fJUMVDTnHPsAYTLx/"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(
            JacksonConverterFactory.create(mapper)
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
