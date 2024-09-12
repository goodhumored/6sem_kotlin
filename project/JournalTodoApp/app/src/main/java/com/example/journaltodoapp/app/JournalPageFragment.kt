package com.example.journaltodoapp.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.journaltodoapp.data.Journal
import com.example.journaltodoapp.R
import com.google.android.material.textfield.TextInputEditText

class JournalPageFragment : Fragment() {

    private lateinit var journal: Journal
    private lateinit var viewModel: JournalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_journal_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titleInput: TextInputEditText = view.findViewById(R.id.journalTitleInput)
        val contentInput: TextInputEditText = view.findViewById(R.id.journalContentInput)
        viewModel = ViewModelProvider(this)[JournalViewModel::class.java]
        val saveButton: Button = view.findViewById(R.id.saveButton)

        val args = arguments?.let { JournalPageFragmentArgs.fromBundle(it) }
        journal = args?.journal ?: return

        titleInput.setText(journal.title);
        contentInput.setText(journal.content);

        saveButton.setOnClickListener {
            val updatedTitle = titleInput.text.toString()
            val updatedContent = contentInput.text.toString()

            if (updatedTitle.isNotBlank() && updatedContent.isNotBlank()) {
                journal = journal.copy(title = updatedTitle, content = updatedContent)
                viewModel.update(journal)
                Toast.makeText(requireContext(), "Успешно сохранено", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Заголовок или текст не могут быть пустыми", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
