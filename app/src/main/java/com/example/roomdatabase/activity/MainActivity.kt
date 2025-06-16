package com.example.roomdatabase

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabase.networkResponse.NetworkResponse
import com.example.roomdatabase.database.entity.Note
import com.example.roomdatabase.databinding.ActivityMainBinding
import com.example.roomdatabase.viewModel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val noteViewModel by viewModels<NoteViewModel>()

    private fun showToast(message: String, length: Int = Toast.LENGTH_SHORT){
        Toast.makeText(this@MainActivity, message, length).show()
    }

    private suspend fun addNote(){
        val name = binding.noteName.text.toString()
        val description = binding.noteDescription.text.toString()
        if(name.isEmpty() || description.isEmpty()){
            showToast("Fields are required", length = Toast.LENGTH_LONG)
        }else{
            val response = noteViewModel.addNote(name = name, description = description)
            when(response){
                is NetworkResponse.Loading -> Unit
                is NetworkResponse.Success -> {
                    showToast(response.value)
                    clearInputs()
                    noteViewModel.getAllNotes()
                }
                is NetworkResponse.Error -> showToast(response.exception.message.toString())
            }
        }
    }

    private fun setInputs(note: Note) {
        binding.apply {
            noteName.setText(note.noteName)
            noteDescription.setText(note.noteDescription)
            headingAddNote.text = getString(R.string.update_note)
            addButton.text = getString(R.string.update)
            noteViewModel.setEditNote(note)
        }
    }
    private fun clearInputs(){
        binding.apply {
            noteName.setText(null)
            noteDescription.setText(null)
            headingAddNote.text = getString(R.string.add_note_heading)
            addButton.text = getString(R.string.add)
            noteViewModel.setEditNote(null)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter: NotesDetailsAdapter = NotesDetailsAdapter(emptyList(), noteViewModel)
        binding.listContainer.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }
        binding.listContainer.adapter = adapter
        noteViewModel.getAllNotes()
        lifecycleScope.launch {
            noteViewModel.notes.observe(this@MainActivity) { response ->
                when (response) {
                    is NetworkResponse.Loading -> {
                        changeVisiblity(view = binding.progressbar, value = View.VISIBLE)
                        changeVisiblity(view = binding.noNotesText, value = View.GONE)
                        changeVisiblity(binding.listContainer, View.GONE)
                        Log.i("noteViewModel", "Loading notes...")
                    }
                    is NetworkResponse.Success -> {
                        changeVisiblity(view = binding.progressbar, value = View.GONE)

                        response.let {
                            if(response.value.isEmpty()){
                                binding.noNotesText.text = getString(R.string.no_notes_found)
                                changeVisiblity(view = binding.noNotesText, value = View.VISIBLE)
                            }else{
                                changeVisiblity(binding.listContainer, View.VISIBLE)
                                changeVisiblity(view = binding.noNotesText, value = View.GONE)
                            }
                            adapter.setListItems(response.value)
                        }
                        Log.i("noteViewModel", "Notes loaded: ${response.value}")
                    }
                    is NetworkResponse.Error -> {
                        changeVisiblity(view = binding.progressbar, value = View.GONE)
                        changeVisiblity(binding.listContainer, View.GONE)
                        binding.noNotesText.text = response.exception.message
                        changeVisiblity(view = binding.noNotesText, value = View.GONE)
                        Log.i("noteViewModel", "Error loading notes: ${response.exception.message}")
                    }
                }
                noteViewModel.editNote.observe(this@MainActivity){ note ->
                    note?.let{
                        setInputs(note)
                    }
                }
            }
            binding.addButton.setOnClickListener {
                lifecycleScope.launch {
                    addNote()
                }
            }
            binding.clearButton.setOnClickListener {
                clearInputs()
            }
        }
    }
    fun changeVisiblity(view: View, value: Int){
        view.visibility = value
    }
}