package com.example.cafesapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Database for the app.
 *
 * Contains the [CafeDao].
 */
@Database(entities = [DbCafe::class], version = 4, exportSchema = false)
abstract class CafeDb : RoomDatabase() {
    /**
     * Gets the [CafeDao].
     *
     * @return [CafeDao].
     */
    abstract fun cafeDao(): CafeDao

    /**
     * Companion object for the database to make sure there is only 1 instance of it.
     */
    companion object {
        @Volatile
        private var Instance: CafeDb? = null

        fun getDatabase(context: Context): CafeDb {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, CafeDb::class.java, "task_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
