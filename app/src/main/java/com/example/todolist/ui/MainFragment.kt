package com.example.todolist.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.adapter.ToDoAdapter
import com.example.todolist.databinding.FragmentMainBinding
import com.example.todolist.model.ToDo
import com.example.todolist.viewmodel.MainFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject


class MainFragment : Fragment()
{
    private val viewModel: MainFragmentViewModel by inject()
    private lateinit var binding: FragmentMainBinding
    private lateinit var recyclerAdapter: ToDoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentMainBinding.inflate(inflater,container,false)

        onClickFab()
        setRecycler()

        return binding.root
    }

    private fun onClickFab()
    {
        binding.fab.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_mainFragment_to_addFragment)
        }
    }

    private fun onLayoutClicked(): (Int) -> Unit
    {
        return { id: Int ->
                    val bundle = bundleOf("id" to id)
                    Navigation.findNavController(binding.root).navigate(R.id.action_mainFragment_to_updateFragment, bundle)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onBtDeleteClicked(): (ToDo) -> Unit {
        return { todo: ToDo ->
                    viewModel.deleteToDo(todo)
                    recyclerAdapter.notifyDataSetChanged()
                    snack(getString(R.string.tarefa_excluida))
        }
    }

    private fun onCBoxClicked(): (ToDo) -> Unit {
        return { todo: ToDo ->
                    viewModel.updateToDo(todo)
                    val recycler : RecyclerView
                }
    }

    private fun setRecycler()
    {
        recyclerAdapter = ToDoAdapter(onBtDeleteClicked(), onLayoutClicked(),onCBoxClicked())
        binding.recycler.apply {
            setHasFixedSize(true)
            adapter = recyclerAdapter
        }
        setObservers()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setObservers ()
    {
        viewModel.allToDos.observe(viewLifecycleOwner, {
            recyclerAdapter.updateArray(ArrayList(it))
            recyclerAdapter.notifyDataSetChanged()
        })
    }

    private fun snack(message: String, duration: Int = Snackbar.LENGTH_LONG) {
        Snackbar.make(binding.root,message,duration).show()
    }
}