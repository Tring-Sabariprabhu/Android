package com.example.roomdatabase.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomdatabase.database.dao.NoteDao
import com.example.roomdatabase.database.entity.Note

@Database(entities = [Note::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
}