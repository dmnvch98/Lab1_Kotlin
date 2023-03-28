package com.example.lab1

object NotesContract {
    // Название таблицы
    const val TABLE_NAME = "notes"
    
    // Столбцы таблицы
    object Columns {
        const val ID = "id"
        const val TITLE = "title"
        const val TEXT = "text"
        const val DATE = "date"
    }
    
    // Создание таблицы
    const val CREATE_TABLE =
        "CREATE TABLE $TABLE_NAME (" +
                "${Columns.ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${Columns.TITLE} TEXT," +
                "${Columns.TEXT} TEXT," +
                "${Columns.DATE} TEXT" +
        ")"
    
    // Удаление таблицы
    const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}
