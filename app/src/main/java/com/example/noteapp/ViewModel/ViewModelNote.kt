package com.example.noteapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.Model.Note
import kotlinx.coroutines.launch

class ViewModelNote (private val repository : NoteRepository) : ViewModel() {



    val allNotes : LiveData<MutableList<Note>> = repository.allNotes



    fun insert(note: Note) = viewModelScope.launch {
        repository.insert(note)
    }

    fun delete() = viewModelScope.launch {
        repository.delete()
    }

    fun deleteItem(note: Note) = viewModelScope.launch {
        repository.deleteItem(note)
    }

    fun getItem(id: Int) = viewModelScope.launch {
        repository.getItem(id)
    }

    fun updateItem(note: Note) = viewModelScope.launch {
        repository.updateItem(note)
    }




}