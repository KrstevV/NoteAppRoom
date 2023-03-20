package com.example.noteapp.Listeners

import com.example.noteapp.Model.Note

interface OnClickListener {

    fun onClickListener(not : Note)

    fun updateOnClickListener(not : Note)


}