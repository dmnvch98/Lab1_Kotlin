import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.lab1.Note
import com.example.lab1.NotesContract

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        // Название базы данных
        const val DATABASE_NAME = "notes.db"
        // Версия базы данных
        const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Создание таблицы при первом запуске приложения
        db.execSQL(NotesContract.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Удаление старой таблицы и создание новой при обновлении базы данных
        db.execSQL(NotesContract.DROP_TABLE)
        onCreate(db)
    }

    @SuppressLint("Range")
    fun getAllNotes(): MutableList<Note> {
        val notesList = mutableListOf<Note>()

        val db = this.readableDatabase

        val query = "SELECT * FROM ${NotesContract.TABLE_NAME} ORDER BY date DESC"

        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex(NotesContract.Columns.ID))
            val title = cursor.getString(cursor.getColumnIndex(NotesContract.Columns.TITLE))
            val content = cursor.getString(cursor.getColumnIndex(NotesContract.Columns.TEXT))
            val date = cursor.getLong(cursor.getColumnIndex(NotesContract.Columns.DATE))

            val note = Note(
                id = id.toLong(),
                title = title,
                content = content,
                createdAt = date
            )

            notesList.add(note)
        }

        cursor.close()

        return notesList
    }

    fun addNote(note: Note) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NotesContract.Columns.TITLE, note.title)
        values.put(NotesContract.Columns.TEXT, note.content)
        values.put(NotesContract.Columns.DATE, note.createdAt)
        db.insert(NotesContract.TABLE_NAME, null, values)
        db.close()
    }

}
