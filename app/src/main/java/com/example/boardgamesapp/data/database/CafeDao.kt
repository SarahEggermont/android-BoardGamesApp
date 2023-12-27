package com.example.boardgamesapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CafeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DbCafe)

    @Update
    suspend fun update(item: DbCafe)

    @Delete
    suspend fun delete(item: DbCafe)

    @Query("SELECT * from cafes WHERE name_nl = :name")
    fun getItem(name: String): Flow<DbCafe>

    @Query("SELECT * from cafes ORDER BY name_nl ASC")
    fun getAllItems(): Flow<List<DbCafe>>

    @Query("SELECT * from cafes WHERE name_nl LIKE :search ORDER BY name_nl ASC")
    fun getAllItems(search: String): Flow<List<DbCafe>>
}
