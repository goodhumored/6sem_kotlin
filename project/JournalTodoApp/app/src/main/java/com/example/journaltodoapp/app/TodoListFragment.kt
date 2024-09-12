package com.example.journaltodoapp.app

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.journaltodoapp.R
import com.example.journaltodoapp.data.TodoTask

class TodoListFragment : Fragment() {

    private lateinit var todoRecyclerView: RecyclerView
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var viewModel: TodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_todolist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        todoRecyclerView = view.findViewById(R.id.recyclerViewTodo)
        viewModel = ViewModelProvider(this)[TodoViewModel::class.java]
        todoAdapter = TodoAdapter(viewModel.allTasks.value, {
            todo ->
            askForNewTaskName(requireContext(), todo.task) {
                text ->
                todo.task = text;
                viewModel.update(todo);
            }
        }, {
            todo, isChecked ->
            todo.isCompleted = isChecked
            viewModel.update(todo);
        } )
        val addButton: Button = view.findViewById(R.id.addTodoButton)

        todoRecyclerView.layoutManager = LinearLayoutManager(context)
        todoRecyclerView.adapter = todoAdapter

        viewModel.allTasks.observe(viewLifecycleOwner, Observer { todos ->
            todoAdapter.submitList(todos)
        })

        addButton.setOnClickListener {
            val count = viewModel.allTasks.value?.count() ?: 0;
            val task = "task"
            val newTodo = TodoTask(count + 1, task, false)
            viewModel.insert(newTodo)
            Toast.makeText(requireContext(), "todo item $task($id) added!", Toast.LENGTH_SHORT).show()
        }
        val itemTouchHelper = ItemTouchHelper(TodoSwipeToDeleteCallback(viewModel));
        itemTouchHelper.attachToRecyclerView(todoRecyclerView)
    }

    private fun askForNewTaskName(context: Context, defaultValue: String, onTextSubmitted: (String) -> Unit) {
        val input = EditText(context)
        input.setText(defaultValue)
        input.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        val dialog = AlertDialog.Builder(context)
            .setTitle("Новый текст задачи: ")
            .setView(input)
            .setPositiveButton("OK") { _, _ ->
                val userInput = input.text.toString()
                onTextSubmitted(userInput)
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }
            .create()

        dialog.show()
    }
}
