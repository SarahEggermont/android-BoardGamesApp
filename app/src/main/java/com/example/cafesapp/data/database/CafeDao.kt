package com.example.cafesapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * DAO for the cafes.
 */
@Dao
interface CafeDao {
    /**
     * Inserts the cafe.
     *
     * @param item: [DbCafe] The cafe to insert or update.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DbCafe)

    /**
     * Updates the cafe.
     *
     * @param item: [DbCafe] The cafe to insert or update.
     */
    @Update
    suspend fun update(item: DbCafe)

    /**
     * Deletes the cafe.
     *
     * @param item: [DbCafe] The cafe to delete.
     */
    @Delete
    suspend fun delete(item: DbCafe)

    /**
     * Gets a cafe by the name of it.
     *
     * @param name: [String] The name of the cafe.
     * @return A cafe, a [Flow] of [DbCafe].
     */
    @Query("SELECT * from cafes WHERE name_nl = :name")
    fun getItem(name: String): Flow<DbCafe>

    /**
     * Gets all cafes.
     *
     * @return The cafes, a [Flow] of a [List] of [DbCafe].
     */
    @Query("SELECT * from cafes ORDER BY name_nl ASC")
    fun getAllItems(): Flow<List<DbCafe>>

    /**
     * Gets all cafes that match the search query.
     *
     * @param search: [String] The search query.
     * @return The cafes, a [Flow] of a [List] of [DbCafe].
     */
    @Query("SELECT * from cafes WHERE name_nl LIKE :search ORDER BY name_nl ASC")
    fun getAllItems(search: String): Flow<List<DbCafe>>
}
