package com.example.todolist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.model.ToDo
import com.example.todolist.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddFragmentViewModel(private val repository: ToDoRepository) : ViewModel() {
    private lateinit var _toDo: ToDo

    fun setToDo(tarefa: String) {
        _toDo = ToDo(tarefa)
    }

    fun insertTodo() {
        viewModelScope.launch(Dispatchers.IO) {

            repository.insertToDO(_toDo)
        }
    }
}
