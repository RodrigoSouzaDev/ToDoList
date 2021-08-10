package com.example.todolist.viewmodel

import androidx.lifecycle.*
import com.example.todolist.model.ToDo
import com.example.todolist.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException


class MainFragmentViewModel (private val repository: ToDoRepository): ViewModel()
{
    val allToDos: LiveData<List<ToDo>> = repository.getToDos().asLiveData()

    fun deleteToDo (toDo: ToDo)
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteToDO(toDo)
        }
    }

    fun updateToDo(todo: ToDo)
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateToDO(todo)
        }
    }
}

class MainFragViewModelFactory(private val repository: ToDoRepository): ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainFragmentViewModel::class.java))
        {
            @Suppress("UNCHECKED_CAST")
            return MainFragmentViewModel(repository) as T
        }
        throw  IllegalArgumentException("Unknown ViewModel class")
    }
}