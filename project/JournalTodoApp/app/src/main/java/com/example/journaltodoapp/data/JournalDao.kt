package com.example.journaltodoapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface JournalDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(journal: Journal)

    @Query("SELECT * FROM journal_table ORDER BY timestamp DESC")
    fun getAllJournals(): LiveData<List<Journal>>

    @Update
    suspend fun updateJournal(task: Journal)

    @Delete
    suspend fun deleteJournal(task: Journal)
}