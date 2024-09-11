package com.example.journaltodoapp.app

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.journaltodoapp.R
import com.example.journaltodoapp.data.Journal

class JournalAdapter(private var journalList: List<Journal>?) : RecyclerView.Adapter<JournalAdapter.JournalViewHolder>() {

    inner class JournalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.journalTitle)
        val contentTextView: TextView = itemView.findViewById(R.id.journalContent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JournalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_journal, parent, false)
        return JournalViewHolder(view)
    }

    override fun getItemCount(): Int {
        val count = journalList?.count() ?: 0;
        Log.d("jour adap", "returning count $count")
     return count;
    }

    override fun onBindViewHolder(holder: JournalViewHolder, position: Int) {
        val journal = journalList?.get(position)
        holder.titleTextView.text = journal?.title ?: "-"
        holder.contentTextView.text = journal?.content ?: "-"
    }

    fun submitList(newList: List<Journal>) {
        journalList = newList
        notifyDataSetChanged()
    }
}
