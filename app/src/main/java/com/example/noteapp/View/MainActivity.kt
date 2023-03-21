package com.example.noteapp.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.Listeners.OnClickListener
import com.example.noteapp.Model.Note
import com.example.noteapp.Model.NoteDatabase
import com.example.noteapp.ViewModel.NoteRepository
import com.example.noteapp.ViewModel.NoteViewModelFactory
import com.example.noteapp.ViewModel.ViewModelNote
import com.example.noteapp.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(){
   lateinit var binding: ActivityMainBinding
    lateinit var Adapter : Adapter
    lateinit var noteViewModel : ViewModelNote


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var note = mutableListOf<Note>()
        val noteDao = NoteDatabase.getInstance(this).noteDao()
        val noteRepository = NoteRepository(noteDao)
        val viewModelFactory = NoteViewModelFactory(noteRepository)

        noteViewModel = ViewModelProvider(this, viewModelFactory).get(ViewModelNote::class.java)

        Adapter = Adapter(note,clickListener)

         binding.recView.apply {
             layoutManager = LinearLayoutManager(this@MainActivity)
             adapter = Adapter
         }

        noteViewModel.allNotes.observe(this, Observer {
            Adapter.setNotesList(it)
        })

        binding.floatingActionButton.setOnClickListener {
            var intent = Intent(this, AddUser::class.java)
            startActivity(intent)
        }
        binding.floatingActionButton2.setOnClickListener {
            builderDeleteAllNote()
        }
    }

    private val clickListener = object : OnClickListener {
        override fun deleteOnClickListener(not: Note) {
            builderDeleteNote(not)
        }
        override fun updateOnClickListener(not: Note) {
            var intentT = Intent(this@MainActivity, UpdateUser::class.java)
            var currentNoteName = not.note
            var currentNoteTxt = not.txt
            var currentNoteId = not.id
            intentT.putExtra("currentNoteName", currentNoteName)
            intentT.putExtra("currentNoteTxt", currentNoteTxt)
            intentT.putExtra("currentNoteId", currentNoteId)
            startActivity(intentT)

            var noteUpdate = intent.getStringExtra("updateNote")
            var txtUpdate = intent.getStringExtra("updateTxt")
            not.note = noteUpdate
            not.txt = txtUpdate
        }
    }
    private fun builderDeleteAllNote(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete all Notes")
        builder.setMessage("Do you want to delete all notes?")
        builder.setPositiveButton("Yes") { dialog, which ->
            noteViewModel.delete()
            dialog.dismiss()
        }
        builder.setNegativeButton("No") { dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }
    private fun builderDeleteNote(not : Note) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete a Note")
        builder.setMessage("Do you want to delete a Note?")
        builder.setPositiveButton("Yes") { dialog, which ->
            noteViewModel.deleteItem(not)
            dialog.dismiss()
        }
        builder.setNegativeButton("No") { dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }
}