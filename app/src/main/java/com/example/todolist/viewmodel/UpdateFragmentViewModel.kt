package com.example.todolist.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.todolist.model.ToDo
import com.example.todolist.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class UpdateFragmentViewModel(private val repository: ToDoRepository) : ViewModel() {

    private lateinit var _toDoLive: LiveData<ToDo>

    fun getToDo(id: Int, lambda: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO)
        {
            _toDoLive = repository.getToDo(id).asLiveData()

            withContext(Dispatchers.Main)
            {
                logIt("valor = ${_toDoLive.value?.tarefa}")
                lambda()
            }
        }
    }

    fun updateToDo() {

        viewModelScope.launch(Dispatchers.IO) {
            repository.updateToDO(_toDoLive.value!!)
        }
    }

    fun getLiveData(): LiveData<ToDo> {
        return _toDoLive
    }
}

fun logIt(mensagem: String) {
    Log.d("update", mensagem)
}
