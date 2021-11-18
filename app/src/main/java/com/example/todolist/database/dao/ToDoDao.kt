package com.example.todolist.database.dao

import androidx.room.*
import com.example.todolist.model.ToDo
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao
{
    @Insert
    suspend fun insert(ToDo: ToDo)

    @Update
    suspend fun update(ToDo: ToDo)

    @Delete
    suspend fun delete(ToDo: ToDo)

    @Query("SELECT * FROM table_todo ORDER BY status DESC")
    fun getAllToDo(): Flow<List<ToDo>>

    @Query("SELECT * FROM table_todo WHERE id = :id")
    fun getToDo(id:Int): Flow<ToDo>
}