package com.example.notes.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.notes.data.models.NotesModel

@Dao
interface NoteDao {

    @Query("SELECT *FROM notes_list ORDER BY id DESC")
    fun getNotes(): List<NotesModel>

    @Upsert
    fun addNotes(notesModel: NotesModel)

    @Delete
    fun deleteNote(notesModel: NotesModel)
}