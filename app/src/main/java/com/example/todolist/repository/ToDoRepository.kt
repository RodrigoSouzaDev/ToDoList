package com.example.todolist.repository

import com.example.todolist.model.ToDo
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {

    fun getToDos(): Flow<List<ToDo>>

    fun getToDo(id: Int): Flow<ToDo>

    suspend fun insertToDO (toDo: ToDo)

    suspend fun updateToDO (toDo: ToDo)

    suspend fun deleteToDO (toDo: ToDo)
}