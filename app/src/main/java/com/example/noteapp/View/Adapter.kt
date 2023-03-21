package com.example.noteapp.View

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.Model.Note
import com.example.noteapp.Listeners.OnClickListener
import com.example.noteapp.R
import java.text.SimpleDateFormat
import java.util.*


class Adapter(private var note: MutableList<Note>,val listener: OnClickListener) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(itemView: View, listener: OnClickListener) : RecyclerView.ViewHolder(itemView) {
        var text1 = itemView.findViewById<TextView>(R.id.textView)
        var text2 = itemView.findViewById<TextView>(R.id.textView2)
        var btnDelete = itemView.findViewById<Button>(R.id.button2)
        val layout = itemView.findViewById<ConstraintLayout>(R.id.item_layout)
        var dateText = itemView.findViewById<TextView>(R.id.textViewDate)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view, listener)
    }

    override fun getItemCount(): Int {
        return note.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var currentPosition = note.get(position)
        holder.text1.text = currentPosition.note
        holder.text2.text = currentPosition.txt
        holder.dateText.text = currentPosition.Date

        holder.layout.setOnClickListener {
             listener.updateOnClickListener(currentPosition)
        }
        holder.btnDelete.setOnClickListener {
           listener.deleteOnClickListener(currentPosition)
        }
    }
    fun setNotesList(notes: MutableList<Note>) {
        this.note = notes
        notifyDataSetChanged()
    }
}








