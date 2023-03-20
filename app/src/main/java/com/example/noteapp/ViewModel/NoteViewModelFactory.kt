package com.example.noteapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NoteViewModelFactory(private val repository: NoteRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelNote::class.java)) {
            return ViewModelNote(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}