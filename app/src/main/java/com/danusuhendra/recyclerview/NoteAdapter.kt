package com.danusuhendra.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_note.view.*

class NoteAdapter(private val listNote: List<Note>) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    private lateinit var listener : OnClickListenerCallback
    fun setOnClickListener(listenerCallback: OnClickListenerCallback){
        this.listener = listenerCallback
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listNote.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listNote[position], position)
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(note: Note, position: Int){
            itemView.tv_title_note.text = note.title
            itemView.tv_content_note.text = note.content
            itemView.tv_detail_note.text = note.detail
            itemView.tv_time_note.text = note.time
            listener.let {
                itemView.setOnClickListener {
                    listener.onClickListener(note,position)
                }
            }
        }
    }

    interface OnClickListenerCallback{
        fun onClickListener(note: Note, position: Int)
    }
}