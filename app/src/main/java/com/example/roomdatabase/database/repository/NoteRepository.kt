package com.example.roomdatabase.database.repository

import com.example.roomdatabase.database.db.Database
import com.example.roomdatabase.database.entity.Note
import javax.inject.Inject


class NoteRepository @Inject constructor(private val db: Database){
    fun getAllNotes(): List<Note> {
        return db.getNoteDao().getAllNotes()
    }
    suspend fun insertNote(note: Note): Long {
        return db.getNoteDao().upsertNote(note)
    }
    suspend fun deleteNote(note: Note) {
        return db.getNoteDao().deleteNote(note)
    }
    suspend fun updateNote(note: Note): Long {
        return db.getNoteDao().upsertNote(note)
    }
}