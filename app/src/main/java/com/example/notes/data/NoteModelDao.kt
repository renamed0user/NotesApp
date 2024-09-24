package com.example.notes.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteModelDao {
    @Upsert
    suspend fun upsertDataModel(noteModel: NoteModel)

    @Query("SELECT * FROM notemodel")
    fun getAllNotes(): Flow<List<NoteModel>>

    @Query("SELECT * FROM notemodel ORDER BY priority DESC")
    fun getAllNotesSortedByPriority(): Flow<List<NoteModel>>

    @Update
    fun updateNote(noteModel: NoteModel)

    @Insert
    fun addNote(noteModel: NoteModel)

    @Delete
    fun deleteNote(noteModel: NoteModel)

}