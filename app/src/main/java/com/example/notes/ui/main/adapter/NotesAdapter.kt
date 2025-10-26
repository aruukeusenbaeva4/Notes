package com.example.notes.ui.main.adapter

import android.content.ClipData
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.data.models.NotesModel
import com.example.notes.databinding.ItemNotesBinding

class NotesAdapter(val onLongClick:(NotesModel) -> Unit): RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    private val notesList = arrayListOf<NotesModel>();

    fun addNotes(notes: List<NotesModel>) {
        notesList.clear()
        notesList.addAll(notes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotesViewHolder {
        return NotesViewHolder(
            ItemNotesBinding.inflate(
                LayoutInflater.from(
                    parent.context),
                parent,false
                    )
            )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
       holder.onBind(notesList[position])
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    inner class NotesViewHolder(private val binding: ItemNotesBinding):
        RecyclerView.ViewHolder(binding.root){
        fun onBind(notesModel: NotesModel){
            binding.tvTitle.text = notesModel.title
            binding.tvDesc.text = notesModel.desc
            binding.tvDate.text = notesModel.date

            itemView.setOnLongClickListener {
                onLongClick(notesModel)
                false
            }
        }
    }
}