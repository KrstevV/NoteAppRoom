package com.example.noteapp.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,
    var note: String?,
    var txt: String?,
    var Date: String?

    )
