package com.teakave.noteapp.presentation.feature.note.overview.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.teakave.domain.feature.note.model.NoteData
import com.teakave.noteapp.databinding.NoteItemBinding
import java.text.SimpleDateFormat

private const val LAST_MODIFIED_SIMPLE_DATE_FORMAT = "dd.MM.yyyy"

class NotesRecyclerAdapter(
    private val clickListener: (NoteData) -> Unit,
    private val deleteClickListener: (Int?) -> Unit
) : RecyclerView.Adapter<NotesRecyclerAdapter.ViewHolder>() {

    private val diffCallBack = object : DiffUtil.ItemCallback<NoteData>() {

        override fun areItemsTheSame(oldItem: NoteData, newItem: NoteData) =
            oldItem.noteId == newItem.noteId

        override fun areContentsTheSame(oldItem: NoteData, newItem: NoteData) = oldItem == newItem

    }

    private val differ = AsyncListDiffer(this, diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(differ.currentList[position], clickListener, deleteClickListener)

    override fun getItemCount() = differ.currentList.size

    fun setNotes(notesData: List<NoteData>) = differ.submitList(notesData)

    class ViewHolder(private val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SimpleDateFormat")
        fun bind(
            noteData: NoteData,
            clickListener: (NoteData) -> Unit,
            deleteClickListener: (Int?) -> Unit
        ) {
            binding.run {
                noteData.apply {

                    title?.let {
                        textNoteItemTitle.text = it
                    }

                    content?.let {
                        textNoteItemContent.text = it
                    }

                    textNoteItemLastUpdate.text =
                        SimpleDateFormat(LAST_MODIFIED_SIMPLE_DATE_FORMAT).format(lastUpdateDate)
                }

                root.setOnClickListener { clickListener(noteData) }
                buttonNoteItemDelete.setOnClickListener { deleteClickListener(noteData.noteId) }
            }

        }
    }

}
