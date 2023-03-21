package com.example.noteapp.ViewModel

import androidx.lifecycle.LiveData
import com.example.noteapp.Model.Note
import com.example.noteapp.Model.NoteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NoteRepository(private val noteDao : NoteDao) {

    var allNotes : LiveData<MutableList<Note>> = noteDao.getAll()
    suspend fun insert(note : Note) {
        withContext(Dispatchers.IO) {
            noteDao.insert(note)
        }
    }
    suspend fun delete() {
        withContext(Dispatchers.IO) {
            noteDao.delete()
        }
    }
    suspend fun deleteItem(note: Note) {
        withContext(Dispatchers.IO) {
            noteDao.deleteItem(note)
        }
    }
    suspend fun getItem(id: Int) {
        withContext(Dispatchers.IO) {
            noteDao.getItem(id)
        }
    }
    suspend fun updateItem(note : Note) {
        withContext(Dispatchers.IO) {
            noteDao.updateItem(note)
        }
    }
}