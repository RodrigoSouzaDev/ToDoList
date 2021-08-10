package com.example.todolist.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.todolist.model.ToDo
import com.example.todolist.repository.ToDoRepository
import kotlinx.coroutines.*
import java.lang.IllegalArgumentException



class UpdateFragmentViewModel(private val repository: ToDoRepository): ViewModel()
{

    private lateinit var _toDoLive: LiveData<ToDo>

    fun getToDo(id:Int, lambda: ()-> Unit)
    {
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

    fun updateToDo ()
    {

        viewModelScope.launch (Dispatchers.IO) {
            repository.updateToDO(_toDoLive.value!!)
        }
    }

    fun getLiveData ():LiveData<ToDo>
    {
        return _toDoLive
    }
}

fun logIt(mensagem: String){
    Log.d("update",mensagem)
}

class UpdateViewModelFactory (private val repository: ToDoRepository): ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UpdateFragmentViewModel::class.java))
        {
            return UpdateFragmentViewModel(repository) as T
        }
        throw  IllegalArgumentException("Unknown ViewModel class")
    }
}