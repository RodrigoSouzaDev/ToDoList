package com.example.todolist.repository

import com.example.todolist.database.ToDoDatabase
import com.example.todolist.model.ToDo
import kotlinx.coroutines.flow.Flow

class ToDoRepository (private val database: ToDoDatabase)
{
    fun getToDos(): Flow<List<ToDo>> {
        //return allToDos
        return database.toDoDao().getAllToDo()
    }

    fun getToDo(id: Int): Flow<ToDo> {
        return database.toDoDao().getToDo(id)
    }

    suspend fun insertToDO (toDo: ToDo)
    {
        database.toDoDao().insert(toDo)
    }

    suspend fun updateToDO (toDo: ToDo)
    {
        database.toDoDao().update(toDo)
    }

    suspend fun deleteToDO (toDo: ToDo)
    {
        database.toDoDao().delete(toDo)
    }
}