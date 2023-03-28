package com.example.lab1

object NotesContract {
    const val TABLE_NAME = "notes"
    
    object Columns {
        const val ID = "id"
        const val TITLE = "title"
        const val TEXT = "text"
        const val DATE = "date"
    }
    
    const val CREATE_TABLE =
        "CREATE TABLE $TABLE_NAME (" +
                "${Columns.ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${Columns.TITLE} TEXT," +
                "${Columns.TEXT} TEXT," +
                "${Columns.DATE} TEXT" +
        ")"
    const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}
