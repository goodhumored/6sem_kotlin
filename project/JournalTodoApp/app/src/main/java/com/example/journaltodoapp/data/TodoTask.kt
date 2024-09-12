package com.example.journaltodoapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "todo_table")
data class TodoTask(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var task: String,
    var isCompleted: Boolean
): Serializable
