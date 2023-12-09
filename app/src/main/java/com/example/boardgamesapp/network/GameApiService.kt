package com.example.boardgamesapp.network

import retrofit2.http.GET

interface GameApiService {

    @GET("hot?type=boardgame")
    suspend fun getTrendingGames(): List<ApiGame>
}
