package com.example.cafesapp

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cafesapp.data.database.CafeDao
import com.example.cafesapp.data.database.CafeDb
import com.example.cafesapp.data.database.asDbCafe
import com.example.cafesapp.data.database.asDomainCafe
import com.example.cafesapp.model.Cafe
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Tests for the [CafeDao].
 *
 * @see CafeDao
 */
@RunWith(AndroidJUnit4::class)
class CafeDaoTest {
    private lateinit var cafeDao: CafeDao
    private lateinit var cafeDb: CafeDb

    private var cafe1 =
        Cafe(
            id = 1,
            nameNl = "Aba-jour",
        )

    private var cafe2 =
        Cafe(
            id = 2,
            nameNl = "Charlatan",
        )

    private suspend fun addOneCafeToDb() {
        cafeDao.insert(cafe1.asDbCafe())
    }

    private suspend fun addTwoCafesToDb() {
        cafeDao.insert(cafe1.asDbCafe())
        cafeDao.insert(cafe2.asDbCafe())
    }

    /**
     * Creates the database.
     */
    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        cafeDb =
            Room.inMemoryDatabaseBuilder(context, CafeDb::class.java)
                // Allowing main thread queries, just for testing.
                .allowMainThreadQueries()
                .build()
        cafeDao = cafeDb.cafeDao()
    }

    /**
     * Closes the database.
     */
    @After
    fun closeDb() {
        cafeDb.close()
    }

    /**
     * Tests [CafeDao.insert].
     */
    @Test
    @Throws(Exception::class)
    fun daoInsert_insertCafeIntoDB() =
        runBlocking {
            addOneCafeToDb()
            val cafes = cafeDao.getAllItems().first()
            assertEquals(cafes[0].asDomainCafe(), cafe1)
        }

    /**
     * Tests [CafeDao.getAllItems].
     */
    @Test
    @Throws(Exception::class)
    fun daoGetAllCafes_returnsAllCafesFromDB() =
        runBlocking {
            addTwoCafesToDb()
            val cafes = cafeDao.getAllItems().first()
            assertEquals(cafes[0].asDomainCafe(), cafe1)
            assertEquals(cafes[1].asDomainCafe(), cafe2)
        }

    /**
     * Tests [CafeDao.getItem].
     */
    @Test
    @Throws(Exception::class)
    fun daoGetCafeByName_returnsCafeFromDB() =
        runBlocking {
            addTwoCafesToDb()
            val cafe = cafeDao.getItem("Charlatan").first()
            assertEquals(cafe.asDomainCafe(), cafe2)
        }

    /**
     * Tests [CafeDao.getAllItems] with search query.
     */
    @Test
    @Throws(Exception::class)
    fun daoGetCafesWithSearchQuery_returnsCafesFromDB() =
        runBlocking {
            addTwoCafesToDb()
            val cafes = cafeDao.getAllItems("Charlatan").first()
            assertEquals(cafes[0].asDomainCafe(), cafe2)
        }

    /**
     * Tests [CafeDao.delete].
     */
    @Test
    @Throws(Exception::class)
    fun daoDeleteAllCafes_deletesAllCafesFromDB() =
        runBlocking {
            addTwoCafesToDb()
            for (cafe in cafeDao.getAllItems().first()) {
                cafeDao.delete(cafe)
            }
            val cafes = cafeDao.getAllItems().first()
            assertEquals(cafes.size, 0)
        }
}
