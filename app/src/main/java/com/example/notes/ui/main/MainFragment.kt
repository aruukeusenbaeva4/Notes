package com.example.notes.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.notes.App
import com.example.notes.R
import com.example.notes.data.local.Pref
import com.example.notes.data.models.NotesModel
import com.example.notes.databinding.FragmentMainBinding
import com.example.notes.ui.main.adapter.NotesAdapter

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val noteAdapter: NotesAdapter = NotesAdapter(::onLongClick)

    private lateinit var pref: Pref
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        pref = Pref(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        getData()
        setupListener()

    }
    private fun onLongClick(notesModel: NotesModel){
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("Delete note?")
            .setPositiveButton("Yes"){dialog, id ->
                App.db.dao().deleteNote(notesModel)
                getData()
            }
            .setNegativeButton("No"){dialog, id ->

            }
        builder.create()
        builder.show()
    }

    private fun setupListener(){
        binding.btnCreate.setOnClickListener {
            findNavController().navigate(R.id.createNotesFragment)
        }
    }

    private fun initView() {
        binding.rvNotes.adapter = noteAdapter
    }
    private fun getData(){
        val notesList = App.db.dao().getNotes()
        noteAdapter.addNotes(notesList)
    }
}

