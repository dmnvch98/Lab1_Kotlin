package com.example.lab1

import DBHelper
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: DBHelper
    private lateinit var notesList: MutableList<Note>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DBHelper(this)
        notesList = dbHelper.getAllNotes()
    }

    private fun showNotes() {
        val listView = findViewById<ListView>(R.id.notes_listview)
        val notesAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, notesList)
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
