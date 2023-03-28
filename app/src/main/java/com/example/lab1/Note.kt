package com.example.lab1

class Note(val id: Long?, var title: String, var content: String, var createdAt: Long) {
    override fun toString(): String {
        return "Note: id=$id, title=$title, content=$content, createdAt=$createdAt"
    }
}
