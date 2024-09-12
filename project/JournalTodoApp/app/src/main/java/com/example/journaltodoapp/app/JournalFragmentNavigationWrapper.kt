package com.example.journaltodoapp.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.journaltodoapp.R

/**
 * A simple [Fragment] subclass.
 * Use the [JournalFragmentNavigationWrapper.newInstance] factory method to
 * create an instance of this fragment.
 */
class JournalFragmentNavigationWrapper : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_journal_navigation_wrap, container, false)
    }
}
