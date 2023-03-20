package com.example.noteapp.Model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.noteapp.Model.Note


@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert (note : Note)

    @Query("SELECT * FROM note_table")
    fun getAll() : LiveData<MutableList<Note>>

    @Query("DELETE FROM note_table")
    fun delete ()

    @Delete
    fun deleteItem(note : Note)

    @Query("SELECT * FROM note_table WHERE id = :itemId")
    fun getItem(itemId: Int): Note

    @Update
    fun updateItem(note: Note)




}