package com.example.boardgamesapp.data

import com.example.boardgamesapp.model.Game
import com.example.boardgamesapp.network.UserApiService

interface UserRepository {
    suspend fun getWishlist(): List<Game>
    suspend fun getLibrary(): List<Game>
}

class ApiUserRepository(private val userApiService: UserApiService) : UserRepository {
    override suspend fun getWishlist(): List<Game> {
        TODO("Not yet implemented")
    }

    override suspend fun getLibrary(): List<Game> {
        TODO("Not yet implemented")
    }
}
