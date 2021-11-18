package com.example.todolist.repository

import com.example.todolist.database.ToDoDatabase
import com.example.todolist.model.ToDo
import kotlinx.coroutines.flow.Flow

class ToDoRepositoryImpl(private val database: ToDoDatabase) : ToDoRepository {

    override fun getToDos(): Flow<List<ToDo>> {
        //return allToDos
        return database.toDoDao().getAllToDo()
    }

    override fun getToDo(id: Int): Flow<ToDo> {
        return database.toDoDao().getToDo(id)
    }

    override suspend fun insertToDO(toDo: ToDo) {
        database.toDoDao().insert(toDo)
    }

    override suspend fun updateToDO(toDo: ToDo) {
        database.toDoDao().update(toDo)
    }

    override suspend fun deleteToDO(toDo: ToDo) {
        database.toDoDao().delete(toDo)
    }
}