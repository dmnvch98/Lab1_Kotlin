package com.example.lab1

import DBHelper
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.DateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: DBHelper
    private lateinit var notesList: MutableList<Note>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DBHelper(this)
        notesList = dbHelper.getAllNotes()
        showNotes()
    }

    private fun showNotes() {
        val listView = findViewById<ListView>(R.id.notes_listview)
        val notesAdapter = object : ArrayAdapter<Note>(this, R.layout.note_item, R.id.note_title_textview, notesList) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                val note = notesList[position]

                val dateTextView = view.findViewById<TextView>(R.id.note_date_textview)
                val titleTextView = view.findViewById<TextView>(R.id.note_title_textview)
                val textTextView = view.findViewById<TextView>(R.id.note_text_textview)

                dateTextView.text = DateFormat.getDateTimeInstance().format(Date(note.createdAt))
                titleTextView.text = note.title
                textTextView.text = note.content

                return view
            }
        }
        listView.adapter = notesAdapter
    }


    private fun clearFields() {
        findViewById<EditText>(R.id.note_title_edittext).setText("")
        findViewById<EditText>(R.id.note_text_edittext).setText("")
    }

    fun addNoteOnClick(view: View) {
        val titleEditText = findViewById<EditText>(R.id.note_title_edittext)
        val textEditText = findViewById<EditText>(R.id.note_text_edittext)

        val title = titleEditText.text.toString()
        val text = textEditText.text.toString()
        if (title.isNotEmpty() && text.isNotEmpty()) {
            val note = Note(
                null,
                title,
                text,
                System.currentTimeMillis()
            )
            dbHelper.addNote(note)
            notesList.clear()
            notesList.addAll(dbHelper.getAllNotes())
            showNotes()
            clearFields()
        } else {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        }
    }

}
