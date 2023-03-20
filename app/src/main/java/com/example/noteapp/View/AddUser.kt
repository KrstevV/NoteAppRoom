package com.example.noteapp.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp.Model.Note
import com.example.noteapp.Model.NoteDao
import com.example.noteapp.Model.NoteDatabase
import com.example.noteapp.ViewModel.NoteRepository
import com.example.noteapp.ViewModel.NoteViewModelFactory
import com.example.noteapp.ViewModel.ViewModelNote
import com.example.noteapp.databinding.ActivityAddUserBinding
import java.text.SimpleDateFormat
import java.util.*

class AddUser : AppCompatActivity() {
    lateinit var binding: ActivityAddUserBinding
    lateinit var noteDao: NoteDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            addNote()
        }

        binding.backButton2.setOnClickListener {
            finish()
        }

    }

    fun addNote() {
        val noteDao = NoteDatabase.getInstance(this).noteDao()
        val noteRepository = NoteRepository(noteDao)
        val viewModelFactory = NoteViewModelFactory(noteRepository)
        val noteViewModel = ViewModelProvider(this, viewModelFactory).get(ViewModelNote::class.java)

        var name = binding.editTextName.text.toString()
        var email = binding.editTexttext.text.toString()



        if (name.isNotEmpty() && email.isNotEmpty()) {
            var note = Note(null, name, email, "Created at ${getCurrentDate()}")
            noteViewModel.insert(note)
            Toast.makeText(applicationContext, "Note is sucessfuly added", Toast.LENGTH_SHORT)
                .show()
            binding.editTextName.text.clear()
            binding.editTexttext.text.clear()
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            intent.putExtra("createdDate", getCurrentDate())
            finish()
        } else {
            Toast.makeText(
                applicationContext,
                "Write a Note Title and Note text",
                Toast.LENGTH_SHORT
            ).show()
        }


    }

    private fun getCurrentDate(): String {
        val currentDate = Calendar.getInstance().time
        val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm")
        return formatter.format(currentDate)


    }
}