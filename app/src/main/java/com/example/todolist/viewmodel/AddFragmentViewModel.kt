package com.example.todolist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.todolist.model.ToDo
import com.example.todolist.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class AddFragmentViewModel (private val repository: ToDoRepository) : ViewModel()
{
    private lateinit var _toDo: ToDo

    fun setToDo (tarefa: String)
    {
        _toDo = ToDo(tarefa)
    }

    fun insertTodo ()
    {
        viewModelScope.launch (Dispatchers.IO){

            repository.insertToDO(_toDo)
        }
    }
}

class AddViewModelFactory(private val repository: ToDoRepository): ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AddFragmentViewModel::class.java))
        {
            return AddFragmentViewModel(repository) as T
        }
        throw  IllegalArgumentException("Unknown ViewModel class")
    }

}