package com.example.roomdatabase.database.db


import android.content.Context
import androidx.room.Room
import com.example.roomdatabase.database.dao.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            "my_database"
        ).build()
    }

    @Provides
    fun provideNoteDao(db: Database): NoteDao {
        return db.getNoteDao()
    }
}
