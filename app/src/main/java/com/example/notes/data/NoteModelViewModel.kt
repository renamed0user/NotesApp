package com.example.notes.data

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow

class NoteModelViewModel(noteDao:NoteModelDao): ViewModel() {

    private val dao= noteDao

    fun getSortedList(): Flow<List<NoteModel>> {
        return dao.getAllNotesSortedByPriority()
    }

    fun add(item:NoteModel){
        dao.addNote(item)
    }

    fun update(item:NoteModel){
        dao.updateNote(item)
    }

    fun delete(item:NoteModel){
        dao.deleteNote(item)
    }
}