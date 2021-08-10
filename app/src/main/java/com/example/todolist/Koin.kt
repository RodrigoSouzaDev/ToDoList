package com.example.todolist

import com.example.todolist.database.ToDoDatabase
import com.example.todolist.repository.ToDoRepository
import com.example.todolist.viewmodel.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module{

    //instância o Repositório
    factory { ToDoRepository(get()) }

    //Instância de ToDoDatabase
    single { ToDoDatabase.getDatabase(androidContext())}

    viewModel {
        MainFragViewModelFactory(get()).create(MainFragmentViewModel::class.java)}

    viewModel {
        AddViewModelFactory(get()).create(AddFragmentViewModel::class.java)}

    viewModel {
        UpdateViewModelFactory(get()).create(UpdateFragmentViewModel::class.java)}

}





