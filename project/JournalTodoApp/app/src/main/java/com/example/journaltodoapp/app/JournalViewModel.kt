package com.example.journaltodoapp.app

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.journaltodoapp.AppDatabase
import com.example.journaltodoapp.data.Journal
import com.example.journaltodoapp.repository.JournalRepository
import kotlinx.coroutines.launch

class JournalViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: JournalRepository
    val allJournals: LiveData<List<Journal>>

    init {
        val journalDao = AppDatabase.getDatabase(application).journalDao()
        repository = JournalRepository(journalDao)
        allJournals = repository.allJournals
    }

    fun insert(journal: Journal) = viewModelScope.launch {
        repository.insert(journal)
    }

    fun update(journal: Journal) = viewModelScope.launch {
        repository.update(journal)
    }

    fun delete(journal: Journal) = viewModelScope.launch {
        repository.delete(journal)
    }
}