package com.teakave.noteapp.presentation.feature.note.overview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.teakave.domain.feature.note.model.NoteData
import com.teakave.noteapp.databinding.NoteItemBinding

class NotesAdapter(
    private val clickListener: (NoteData) -> Unit,
    private val deleteClickListener: (Int?) -> Unit
) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    private var notes = listOf<NoteData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    class ViewHolder(private val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            noteData: NoteData,
            clickListener: (NoteData) -> Unit,
            deleteClickListener: (Int?) -> Unit
        ) {
            noteData.title?.let {
                binding.textNoteItemTitle.text = it
            }
            noteData.content?.let {
                binding.textNoteItemContent.text = it
            }
            binding.root.setOnClickListener { clickListener(noteData) }
            binding.buttonNoteItemDelete.setOnClickListener { deleteClickListener(noteData.noteId) }
        }

    }

}
