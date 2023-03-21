package com.example.noteapp.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp.Model.Note
import com.example.noteapp.Model.NoteDatabase
import com.example.noteapp.ViewModel.NoteRepository
import com.example.noteapp.ViewModel.NoteViewModelFactory
import com.example.noteapp.ViewModel.ViewModelNote
import com.example.noteapp.databinding.ActivityUpdateUserBinding
import java.text.SimpleDateFormat
import java.util.*

class UpdateUser : AppCompatActivity() {
    lateinit var updateBinding: ActivityUpdateUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateBinding = ActivityUpdateUserBinding.inflate(layoutInflater)
        setContentView(updateBinding.root)

        var noteName = updateBinding.editTextNameUpdate
        var email2 = updateBinding.editTexttextUpdate

        val currentNoteName = intent.getStringExtra("currentNoteName")
        val currentNoteTxt = intent.getStringExtra("currentNoteTxt")
        noteName.setText(currentNoteName)
        email2.setText(currentNoteTxt)

        updateBinding.buttonUpdate.setOnClickListener {
            updateNote()
        }

        updateBinding.backButton.setOnClickListener {
            finish()
        }

    }


    fun updateNote() {
        val noteDao = NoteDatabase.getInstance(this).noteDao()
        val noteRepository = NoteRepository(noteDao)
        val viewModelFactory = NoteViewModelFactory(noteRepository)
        val noteViewModel = ViewModelProvider(this, viewModelFactory).get(ViewModelNote::class.java)

        var name = updateBinding.editTextNameUpdate.text.toString()
        var email = updateBinding.editTexttextUpdate.text.toString()
        var currentId = intent.getIntExtra("currentNoteId", -1)


        if (name.isNotEmpty() && email.isNotEmpty()) {
            var not = Note(currentId, name, email, "Updated at ${getCurrentDate()}")
            noteViewModel.updateItem(not)
            var intent = Intent(this, MainActivity::class.java)
            intent.putExtra("updateNote", name)
            intent.putExtra("updateTxt", email)
            startActivity(intent)
            intent.putExtra("updateDate", getCurrentDate())
            finish()
            Toast.makeText(applicationContext, "Note is sucessfuly Updated", Toast.LENGTH_SHORT)
                .show()

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

