package com.first.evansoohoo.carbcounter

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


// Annotates class to be a Room Database with a table (entity) of the Carb class
@Database(entities = arrayOf(CarbEntry::class), version = 1, exportSchema = false)
public abstract class CarbRoomDatabase : RoomDatabase() {

    abstract fun CarbEntry(): CarbEntryDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: CarbRoomDatabase? = null

        fun getDatabase(context: Context): CarbRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        //CarbRoomDatabase::class.java, //ES
                        CarbRoomDatabase::class.java,
                        "carb_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}