package com.teakave.noteapp.presentation.feature.note.overview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.teakave.domain.feature.note.model.NoteData
import com.teakave.noteapp.R
import kotlinx.android.synthetic.main.note_item.view.button_note_item_delete
import kotlinx.android.synthetic.main.note_item.view.text_note_item_content
import kotlinx.android.synthetic.main.note_item.view.text_note_item_title

class NotesAdapter(
    private val clickListener: (NoteData) -> Unit,
    private val deleteClickListener: (Int?) -> Unit
) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    private var notes = listOf<NoteData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.note_item,
            parent,
            false
        )
    )

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(notes[position], clickListener, deleteClickListener)

    fun setNotes(notesData: List<NoteData>) {
        val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                notes[oldItemPosition].noteId == notesData[newItemPosition].noteId

            override fun getOldListSize(): Int = notes.size

            override fun getNewListSize(): Int = notesData.size

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                notes[oldItemPosition] == notesData[newItemPosition]
        })

        notes = notesData
        result.dispatchUpdatesTo(this)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(
            noteData: NoteData,
            clickListener: (NoteData) -> Unit,
            deleteClickListener: (Int?) -> Unit
        ) {
            noteData.title?.let {
                itemView.text_note_item_title.text = it
            }
            noteData.content?.let {
                itemView.text_note_item_content.text = it
            }
            itemView.setOnClickListener { clickListener(noteData) }
            itemView.button_note_item_delete.setOnClickListener { deleteClickListener(noteData.noteId) }
        }

    }

}
