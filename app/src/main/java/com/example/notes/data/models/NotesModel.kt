package com.example.notes.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "notes_list")

data class NotesModel(
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    val title: String,
    val desc: String,
    val date: String
): Serializable
