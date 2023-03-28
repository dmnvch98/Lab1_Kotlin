package com.example.lab1

import android.content.ContentValues
import android.content.Context

class Note(val id: Long?, var title: String, var content: String, var createdAt: Long) {

    companion object {
        const val TABLE_NAME = "notes"
        const val COLUMN_ID = "_id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_CONTENT = "content"
        const val COLUMN_CREATED_AT = "created_at"

        fun all(context: Context): List<Note> {
            val dbHelper = DBHelper(context)
            val cursor = dbHelper.readableDatabase.query(
                TABLE_NAME,
                arrayOf(COLUMN_ID, COLUMN_TITLE, COLUMN_CONTENT, COLUMN_CREATED_AT),
                null,
                null,
                null,
                null,
                "$COLUMN_CREATED_AT DESC"
            )
            val notes = mutableListOf<Note>()
            while (cursor.moveToNext()) {
                val id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID))
                val title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE))
                val content = cursor.getString(cursor.getColumnIndex(COLUMN_CONTENT))
                val createdAt = cursor.getLong(cursor.getColumnIndex(COLUMN_CREATED_AT))
                notes.add(Note(id, title, content, createdAt))
            }
            cursor.close()
            dbHelper.close()
            return notes
        }
    }

    fun save(context: Context): Note {
        val dbHelper = DBHelper(context)
        val values = ContentValues()
        values.put(COLUMN_TITLE, title)
        values.put(COLUMN_CONTENT, content)
        values.put(COLUMN_CREATED_AT, createdAt)
        val db = dbHelper.writableDatabase
        val id = if (this.id == null) {
            db.insert(TABLE_NAME, null, values)
        } else {
            values.put(COLUMN_ID, this.id)
            db.update(TABLE_NAME, values, "$COLUMN_ID = ?", arrayOf(this.id.toString()))
            this.id
        }
        dbHelper.close()
        return Note(id, title, content, createdAt)
    }

}
