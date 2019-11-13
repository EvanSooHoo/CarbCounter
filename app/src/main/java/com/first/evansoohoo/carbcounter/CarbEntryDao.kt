package com.first.evansoohoo.carbcounter

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.lifecycle.LiveData

@Dao
interface CarbEntryDAO {

    @Query("SELECT * FROM carb_table")
    fun getAll(): List<CarbEntry>

    @Query("SELECT * from carb_table ORDER BY CarbEntry ASC")
    fun getAllCarbEntries(): LiveData<List<CarbEntry>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(CarbEntry: CarbEntry)

    @Insert
    fun insertAll(vararg thisCarbEntry: CarbEntry)

    @Query("DELETE FROM carb_table")
    suspend fun deleteAll()
}