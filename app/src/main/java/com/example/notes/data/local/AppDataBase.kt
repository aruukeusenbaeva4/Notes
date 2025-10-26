package com.example.notes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notes.data.models.NotesModel

@Database(entities = [NotesModel::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun dao(): NoteDao
}