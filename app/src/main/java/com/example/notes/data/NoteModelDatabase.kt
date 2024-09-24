package com.example.notes.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NoteModel::class], version = 1)
abstract class NoteModelDatabase:RoomDatabase() {
    abstract val dao:NoteModelDao
}