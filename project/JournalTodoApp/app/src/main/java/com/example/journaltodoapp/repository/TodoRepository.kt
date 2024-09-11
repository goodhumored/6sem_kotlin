package com.example.journaltodoapp.repository

import androidx.lifecycle.LiveData
import com.example.journaltodoapp.data.TodoDao
import com.example.journaltodoapp.data.TodoTask


class TodoRepository(private val todoDao: TodoDao) {
    val allTasks: LiveData<List<TodoTask>> = todoDao.getAllTasks()

    suspend fun insert(task: TodoTask) {
        todoDao.insert(task)
    }
    suspend fun update(task: TodoTask) {
        todoDao.updateTask(task)
    }
    suspend fun delete(task: TodoTask) {
        todoDao.deleteTask(task)
    }
}