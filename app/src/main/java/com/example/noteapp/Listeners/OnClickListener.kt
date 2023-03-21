package com.example.noteapp.Listeners

import com.example.noteapp.Model.Note

interface OnClickListener {
    fun deleteOnClickListener(not : Note)
    fun updateOnClickListener(not: Note)

}