package com.example.boardgamesapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Query("SELECT * from games WHERE title = :title")
    fun getItem(title: String): Flow<DatabaseGame>

    @Query("SELECT * from games WHERE id = :id")
    fun getGameById(id: String): Flow<DatabaseGame>

    @Query("SELECT * from games ORDER BY title ASC")
    fun getAllItems(): Flow<List<DatabaseGame>>

    @Query("SELECT * from games WHERE title LIKE '%' || :search || '%'")
    fun getSearchGame(search: String): Flow<List<DatabaseGame>>

    @Query("SELECT * from games WHERE isFavourite == 1")
    fun getFavouriteGames(): Flow<List<DatabaseGame>>

    @Query("SELECT * from games WHERE inLibrary == 1")
    fun getLibraryGames(): Flow<List<DatabaseGame>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(item: DatabaseGame)

    @Update
    suspend fun updateGame(item: DatabaseGame)

    @Delete
    suspend fun deleteGame(item: DatabaseGame)
}
