package com.example.todolist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.todolist.model.ToDo
import com.example.todolist.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainFragmentViewModel(private val repository: ToDoRepository) : ViewModel() {
    val allToDos: LiveData<List<ToDo>> = repository.getToDos().asLiveData()

    fun deleteToDo(toDo: ToDo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteToDO(toDo)
        }
    }

    fun updateToDo(todo: ToDo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateToDO(todo)
        }
    }
}

