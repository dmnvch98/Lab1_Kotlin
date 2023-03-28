import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
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
}
