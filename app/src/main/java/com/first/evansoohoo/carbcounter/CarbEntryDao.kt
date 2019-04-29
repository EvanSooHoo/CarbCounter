package com.first.evansoohoo.carbcounter

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface CarbEntryDao {
    /*
    @Insert()
    fun insertCarbEntry(user: CarbEntry )

    @Query("SELECT * FROM carbentry")
    fun getAll(): List<CarbEntry>

    @Query("SELECT * FROM carbentry WHERE uid IN (:carbEntryIds)")
    fun loadAllByIds(carbEntryIds: IntArray): List<CarbEntry>
*/
    //@Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
    //        "last_name LIKE :last LIMIT 1")
    //fun findByName(first: String, last: String): User

    //@Insert
    //fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)
}