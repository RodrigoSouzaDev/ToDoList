package com.example.todolist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todolist.database.dao.ToDoDao
import com.example.todolist.model.ToDo

@Database(entities = [ToDo::class], version =1 , exportSchema = false)
abstract class ToDoDatabase : RoomDatabase()
{
    abstract fun toDoDao():ToDoDao

    companion object {

        private const val DATABASE_NAME: String = "Banco_ToDo"

        @Volatile
        private var INSTANCE:ToDoDatabase? = null

        fun getDatabase (context: Context): ToDoDatabase
        {
            val tempInstance = INSTANCE
            if(tempInstance != null)
            {
                return tempInstance
            }
            synchronized(this)
            {
             val instance = Room.databaseBuilder(
                 context.applicationContext,
                 ToDoDatabase::class.java,
                 DATABASE_NAME
             ).build()
             INSTANCE = instance
             return instance
            }
        }
    }
}