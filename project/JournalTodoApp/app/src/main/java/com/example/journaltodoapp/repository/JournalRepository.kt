package com.example.journaltodoapp.repository

import androidx.lifecycle.LiveData
import com.example.journaltodoapp.data.Journal
import com.example.journaltodoapp.data.JournalDao

class JournalRepository(private val journalDao: JournalDao) {
    val allJournals: LiveData<List<Journal>> = journalDao.getAllJournals()

    suspend fun insert(journal: Journal) {
        journalDao.insert(journal)
    }
    suspend fun update(journal: Journal) {
        journalDao.updateJournal(journal)
    }
    suspend fun delete(journal: Journal) {
        journalDao.deleteJournal(journal)
    }
}