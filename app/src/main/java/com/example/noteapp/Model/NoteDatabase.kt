package com.example.noteapp.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Note::class], version = 6)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao() : NoteDao


    companion object {

        @Volatile
        var INSTANCE: NoteDatabase? = null


        fun getInstance(context: Context): NoteDatabase {
            var tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                var instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_datebase"
                ).fallbackToDestructiveMigration().build()

                INSTANCE = instance
                return instance


            }


        }
    }


    }



