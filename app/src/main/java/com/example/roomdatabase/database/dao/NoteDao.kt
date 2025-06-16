package com.example.roomdatabase.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.roomdatabase.database.entity.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes")
    fun getAllNotes(): List<Note>

    @Upsert
    suspend fun upsertNote(note: Note): Long

    @Delete
    suspend fun deleteNote(note: Note): Unit
}