package com.example.notes.ui.create_notes

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notes.App
import com.example.notes.R
import com.example.notes.data.models.NotesModel
import com.example.notes.databinding.FragmentCreateBinding
import java.time.LocalDate
import java.time.LocalDate.now
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CreateNotesFragment : Fragment() {
    private lateinit var binding: FragmentCreateBinding
    private val args: CreateNotesFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val now = LocalDateTime.now()
        val customFormatter = DateTimeFormatter.ofPattern("dd MMMM HH:mm")
        val formattedDateTimeCustom = now.format(customFormatter)
        binding.tvDate.text = formattedDateTimeCustom
        val notesModel =  args.note

        notesModel.let { it ->
            binding.etTitle.setText(it?.title)
            binding.etDesc.setText(it?.desc)
            binding.btnSave.text = "Update"
        }


        binding.btnSave.setOnClickListener {
            val title: String =binding.etTitle.text.toString()
            val desc: String = binding.etTitle.text.toString()
            val date: String = binding.tvDate.text.toString()
            val notesModel = args.note
            if(notesModel == null){
                App.db.dao().addNotes(NotesModel(title = title, desc = desc, date = date))
            }else{
                App.db.dao().addNotes(NotesModel(
                    id = notesModel.id,
                    title = title,
                    desc = desc,
                    date = date))

            }


            findNavController().navigateUp()
        }
    }
}