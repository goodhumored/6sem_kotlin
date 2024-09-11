package com.example.journaltodoapp.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.journaltodoapp.R
import com.example.journaltodoapp.data.TodoTask

class TodoAdapter(private var todoList: List<TodoTask>?) : RecyclerView.Adapter<TodoAdapter.TodoTaskViewHolder>() {

    inner class TodoTaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.journalTitle)
        val contentTextView: TextView = itemView.findViewById(R.id.journalContent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoTaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_journal, parent, false)
        return TodoTaskViewHolder(view)
    }

    override fun getItemCount(): Int {
     return todoList?.count() ?: 0;
    }

    override fun onBindViewHolder(holder: TodoTaskViewHolder, position: Int) {
        val todo = todoList?.get(position)
        holder.titleTextView.text = todo?.task
    }

    fun submitList(newList: List<TodoTask>) {
        todoList = newList
        notifyDataSetChanged()
    }
}
