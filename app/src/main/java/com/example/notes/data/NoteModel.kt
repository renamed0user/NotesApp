package com.example.notes.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteModel(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val name:String,
    val text:String,
    val priority:Int=0
)
