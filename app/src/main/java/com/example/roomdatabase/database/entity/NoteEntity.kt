package com.example.roomdatabase.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val noteName: String,
    val noteDescription: String,
)