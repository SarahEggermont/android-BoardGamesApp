package com.example.boardgamesapp.network

import retrofit2.http.GET

interface GameApiService {

    @GET("?url=https://boardgamegeek.com/xmlapi2/hot?type=boardgame")
    suspend fun getTrendingGames(): Wrapper

    @GET("?url=https://boardgamegeek.com/xmlapi2/search?query={searchTerm}")
    suspend fun getSearchGame(searchTerm: String): WrapperSearch
}
