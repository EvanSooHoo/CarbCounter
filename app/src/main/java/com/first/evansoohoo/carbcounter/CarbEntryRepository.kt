package com.first.evansoohoo.carbcounter
import androidx.lifecycle.LiveData

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class CarbEntryRepository(private val carbEntryDAO: CarbEntryDAO) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allWords: LiveData<List<CarbEntry>> = carbEntryDAO.getAllCarbEntries()


    // The suspend modifier tells the compiler that this must be called from a
    // coroutine or another suspend function.
    suspend fun insert(carbEntry: CarbEntry) {
        carbEntryDAO.insert(carbEntry)
    }
}