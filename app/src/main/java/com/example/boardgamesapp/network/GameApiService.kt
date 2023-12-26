package com.example.boardgamesapp.network

import com.example.boardgamesapp.network.detail.WrapperDetail
import com.example.boardgamesapp.network.list.Wrapper
import com.example.boardgamesapp.network.search.WrapperSearch
import retrofit2.http.GET
import retrofit2.http.Url

interface GameApiService {

    @GET
    suspend fun getTrendingGames(@Url url: String): Wrapper

    @GET
    suspend fun getSearchGame(@Url url: String): WrapperSearch

    @GET
    suspend fun getGame(@Url url: String): WrapperDetail
}