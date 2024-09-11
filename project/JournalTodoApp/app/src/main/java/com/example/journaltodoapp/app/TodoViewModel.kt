package com.example.journaltodoapp.app

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.journaltodoapp.AppDatabase
import com.example.journaltodoapp.data.TodoTask
import com.example.journaltodoapp.repository.TodoRepository
import kotlinx.coroutines.launch


class TodoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TodoRepository
    val allTasks: LiveData<List<TodoTask>>

    init {
        val todoDao = AppDatabase.getDatabase(application).todoDao()
        repository = TodoRepository(todoDao)
        allTasks = repository.allTasks
    }

    fun insert(task: TodoTask) = viewModelScope.launch {
        repository.insert(task)
    }

    fun update(task: TodoTask) = viewModelScope.launch {
        repository.update(task)
    }

    fun delete(task: TodoTask) = viewModelScope.launch {
        repository.delete(task)
    }
}