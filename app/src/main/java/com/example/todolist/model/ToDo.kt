package com.example.todolist.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_todo")
class ToDo (

    var tarefa: String,
    var status : Boolean = false)
{
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}