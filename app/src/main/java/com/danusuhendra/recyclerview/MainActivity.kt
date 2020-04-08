package com.danusuhendra.recyclerview

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_dialog.view.*

class MainActivity : AppCompatActivity() {
    private lateinit var notelist: MutableList<Note>
    private lateinit var adapter: NoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notelist = mutableListOf()
        notelist.add(
            Note(
                "Work From Home",
                "Karena COVID-19",
                "Lorem, ipsum dolor sit amet consectetur adipisicing elit. A inventore iure quidem eveniet quas nesciunt nisi labore sint. Numquam quidem minus quibusdam? Amet quidem, magnam mollitia in autem veritatis nobis?",
                "12.01"
            )
        )
        notelist.add(
            Note(
                "Work From Room",
                "Karena COVID-19",
                "Lorem, ipsum dolor sit amet consectetur adipisicing elit. A inventore iure quidem eveniet quas nesciunt nisi labore sint. Numquam quidem minus quibusdam? Amet quidem, magnam mollitia in autem veritatis nobis?",
                "12.44"
            )
        )
        notelist.add(
            Note(
                "Work Smart",
                "Karena COVID-19",
                "Lorem, ipsum dolor sit amet consectetur adipisicing elit. A inventore iure quidem eveniet quas nesciunt nisi labore sint. Numquam quidem minus quibusdam? Amet quidem, magnam mollitia in autem veritatis nobis?",
                "12.22"
            )
        )
        notelist.add(
            Note(
                "Work From Home",
                "Karena COVID-19",
                "Lorem, ipsum dolor sit amet consectetur adipisicing elit. A inventore iure quidem eveniet quas nesciunt nisi labore sint. Numquam quidem minus quibusdam? Amet quidem, magnam mollitia in autem veritatis nobis?",
                "13.53"
            )
        )
        notelist.add(
            Note(
                "Work From Home",
                "Karena COVID-19",
                "Lorem, ipsum dolor sit amet consectetur adipisicing elit. A inventore iure quidem eveniet quas nesciunt nisi labore sint. Numquam quidem minus quibusdam? Amet quidem, magnam mollitia in autem veritatis nobis?",
                "13.01"
            )
        )
        notelist.add(
            Note(
                "Work Terus",
                "Karena COVID-19",
                "Lorem, ipsum dolor sit amet consectetur adipisicing elit. A inventore iure quidem eveniet quas nesciunt nisi labore sint. Numquam quidem minus quibusdam? Amet quidem, magnam mollitia in autem veritatis nobis?",
                "14.21"
            )
        )
        notelist.add(
            Note(
                "Work Terus",
                "Karena COVID-19",
                "Lorem, ipsum dolor sit amet consectetur adipisicing elit. A inventore iure quidem eveniet quas nesciunt nisi labore sint. Numquam quidem minus quibusdam? Amet quidem, magnam mollitia in autem veritatis nobis?",
                "14.11"
            )
        )

        adapter = NoteAdapter(notelist)
        adapter.setOnClickListener(object : NoteAdapter.OnClickListenerCallback{
            override fun onClickListener(note: Note, position: Int) {
                showEditDialog(note, position)
            }

        })
        fab_add_data.setOnClickListener {
//            notelist.add(Note("Bangun ${Random.nextInt(100)}", "Bangun dari mimpi ${Random.nextInt(1000)}", "Bangun dari mimpi ${Random.nextInt(1000)}", "Bangun dari mimpi ${Random.nextInt(1000)}"))
//            adapter.notifyDataSetChanged()
            showAddDialog()
        }
        rv_note.layoutManager = GridLayoutManager(this, 2)
        rv_note.setHasFixedSize(true)
        rv_note.adapter = adapter

    }

    private fun showAddDialog() {
        this.let {
            val builder = AlertDialog.Builder(this)
            val inflater = this.layoutInflater
            val myView = inflater.inflate(R.layout.custom_dialog, null)
            builder.setView(myView)
            builder.setPositiveButton("SIMPAN") { _, _ ->
                val title = myView.edt_title_edit.text.toString()
                val content = myView.edt_content_edit.text.toString()
                val detail = myView.edt_detail_edit.text.toString()
                val time = myView.edt_time_edit.text.toString()
                notelist.add(Note(title, content, detail, time))
                adapter.notifyDataSetChanged()
            }
            builder.setNegativeButton("BATAL"){dialogInterface, _ ->
                dialogInterface.cancel()
            }

            builder.show()
        }
    }

    fun showEditDialog(note: Note, position : Int){
        val builder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val myView = inflater.inflate(R.layout.custom_dialog_edit, null)
        myView.edt_title_edit.setText(note.title)
        myView.edt_content_edit.setText(note.content)
        myView.edt_detail_edit.setText(note.detail)
        myView.edt_time_edit.setText(note.time)
        builder.setView(myView)
        builder.setNeutralButton("BATAL"){dialog, _ ->
            dialog.cancel()
        }
        builder.setPositiveButton("UPDATE"){_,_ ->
            val title = myView.edt_title_edit.text.toString()
            val content = myView.edt_content_edit.text.toString()
            val details = myView.edt_detail_edit.text.toString()
            val time = myView.edt_time_edit.text.toString()
            notelist[position] = Note(title, content, details, time)
            adapter.notifyDataSetChanged()
        }
        builder.setNegativeButton("DELETE"){_,_ ->
            notelist.removeAt(position)
            adapter.notifyDataSetChanged()
        }
        builder.show()
    }
}
