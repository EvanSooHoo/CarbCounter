package com.first.evansoohoo.carbcounter

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface CarbEntryDAO {

    @Query("SELECT * from carb_table ORDER BY CarbEntry ASC")
    fun getAllCarbEntries(): List<CarbEntry>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(CarbEntry: CarbEntry)

    @Query("DELETE FROM carb_table")
    suspend fun deleteAll()
}