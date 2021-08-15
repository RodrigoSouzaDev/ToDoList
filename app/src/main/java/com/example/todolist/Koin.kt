package com.example.todolist

import com.example.todolist.database.ToDoDatabase
import com.example.todolist.repository.ToDoRepository
import com.example.todolist.repository.ToDoRepositoryImpl
import com.example.todolist.viewmodel.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module{

    //instância o Repositório
    factory<ToDoRepository> { ToDoRepositoryImpl(get()) }

    //Instância de ToDoDatabase
    single { ToDoDatabase.getDatabase(androidContext())}

    viewModel { MainFragmentViewModel(get())}

    viewModel {AddFragmentViewModel(get())}

    viewModel {UpdateFragmentViewModel(get())}

}





