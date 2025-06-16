package com.example.roomdatabase.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdatabase.database.repository.NoteRepository
import com.example.roomdatabase.networkResponse.NetworkResponse
import com.example.roomdatabase.database.entity.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {
    private val _notes = MutableLiveData<NetworkResponse<List<Note>>>()
    val notes : LiveData<NetworkResponse<List<Note>>> get() = _notes

    private val _editNote = MutableLiveData<Note?>()
    val editNote : LiveData<Note?> get() =  _editNote

    fun getAllNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            _notes.postValue(NetworkResponse.Loading)
            try {
                val response = noteRepository.getAllNotes()
                _notes.postValue(NetworkResponse.Success(response))
            }catch (e: Exception){
                _notes.postValue(NetworkResponse.Error(e))
            }
        }
    }
    suspend fun addNote(name: String, description: String): NetworkResponse<String> {
        return withContext(Dispatchers.IO) {
            try {
                _editNote.value.let {
                    if(it == null){
                        val note = Note(noteName = name, noteDescription = description)
                        noteRepository.insertNote(note)
                        NetworkResponse.Success("Note Added Successfully")
                    }else{
                        val note = Note(noteName = name, noteDescription = description, id = it.id)
                        noteRepository.updateNote(note)
                        NetworkResponse.Success("Note Updated Successfully")
                    }
                }
            } catch (e: Exception) {
                NetworkResponse.Error(e)
            }
        }
    }
    fun setEditNote(note: Note?){
        _editNote.postValue(note)
    }
    suspend fun deleteNote(note: Note): NetworkResponse<String>{
        return withContext(Dispatchers.IO){
            try{
                noteRepository.deleteNote(note)
                NetworkResponse.Success("Note deleted Successfully")
            }catch(e: Exception){
                NetworkResponse.Error(e)
            }
        }
    }
}
