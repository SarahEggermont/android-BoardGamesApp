package com.example.boardgamesapp.network

import com.example.boardgamesapp.model.Game
import retrofit2.http.GET
import retrofit2.http.Url

interface UserApiService {
    @GET
    suspend fun getWishlist(@Url url: String): List<Game>

    @GET
    suspend fun getFavourites(@Url url: String): List<Game>
}
