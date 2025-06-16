package com.example.roomdatabase
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.database.entity.Note
import com.example.roomdatabase.databinding.NoteListItemBinding
import com.example.roomdatabase.viewModel.NoteViewModel

class NotesDetailsAdapter(var list: List<Note>, val noteViewModel: NoteViewModel): RecyclerView.Adapter<NotesDetailsAdapter.ViewHolder>() {

    fun setListItems(list: List<Note>){
        this.list = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val noteName = itemView.findViewById<TextView>(R.id.note_name)
        val noteDescription = itemView.findViewById<TextView>(R.id.note_description)
        fun bindItems(note: Note){
            itemView.findViewById<ImageView>(R.id.edit_button).setOnClickListener {
                noteViewModel.setEditNote(note)
            }
            noteName.text = note.noteName[0].toString().uppercase() + note.noteName.substring(1)
            noteDescription.text = note.noteDescription

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }
}