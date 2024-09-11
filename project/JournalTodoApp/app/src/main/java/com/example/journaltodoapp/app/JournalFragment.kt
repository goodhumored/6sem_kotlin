package com.example.journaltodoapp.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.journaltodoapp.R
import com.example.journaltodoapp.data.Journal
import java.util.Calendar

/**
 * A simple [Fragment] subclass.
 * Use the [JournalFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class JournalFragment : Fragment() {
    private lateinit var journalRecyclerView: RecyclerView
    private lateinit var journalAdapter: JournalAdapter // Define this adapter for displaying journal entries
    private lateinit var viewModel: JournalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_journal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        journalRecyclerView = view.findViewById(R.id.recyclerViewJournal)
        viewModel = ViewModelProvider(this)[JournalViewModel::class.java]
        journalAdapter = JournalAdapter(viewModel.allJournals.value) // Initialize your adapter
        val addButton: Button = view.findViewById(R.id.addJournalButton)

        journalRecyclerView.layoutManager = LinearLayoutManager(context)
        journalRecyclerView.adapter = journalAdapter
        viewModel.allJournals.observe(viewLifecycleOwner, Observer { journals ->
            Toast.makeText(requireContext(), "updating journals", Toast.LENGTH_SHORT).show()
            journalAdapter.submitList(journals)
        })

        addButton.setOnClickListener {
            var title = "title"
            var content = "content"
            val count = viewModel.allJournals.value?.count() ?: 0;
            val journal = Journal(count + 1, title, content, Calendar.getInstance().time.time)
            viewModel.insert(journal)
            Toast.makeText(requireContext(), "journal item $title($id) added!", Toast.LENGTH_SHORT).show()
        }
        val itemTouchHelper = ItemTouchHelper(JournalSwipeToDeleteCallback(viewModel));
        itemTouchHelper.attachToRecyclerView(journalRecyclerView)
    }
}
