package com.example.todolist.ui

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.Navigation
import com.example.todolist.R
import com.example.todolist.databinding.FragmentUpdateBinding
import com.example.todolist.viewmodel.UpdateFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject

class UpdateFragment : Fragment()
{
    private val viewModel : UpdateFragmentViewModel by inject()
    private lateinit var binding: FragmentUpdateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)
        arguments?.getInt("id")?.let { viewModel.getToDo(it,setMethods()) }

        return binding.root
    }

    private fun onEditTextChanged()
    {
        binding.editUpdateTarefa.doAfterTextChanged {
            viewModel.getLiveData().value!!.tarefa = binding.editUpdateTarefa.text.toString()
        }
    }

    private fun setMethods(): () -> Unit
    {
        return {
            btOnClick()
            switchOnClick()
            onEditTextChanged()
            setupObservers()
        }
    }

    private fun switchOnClick()
    {
        binding.switchUpdateTarefa.setOnClickListener{
            viewModel.getLiveData().value!!.status = binding.switchUpdateTarefa.isChecked
            configSwitch(binding.switchUpdateTarefa.isChecked)
        }
    }

    private fun configSwitch (bool: Boolean)
    {
        when(bool)
        {
            true ->
            {
                binding.switchUpdateTarefa.text = getString(R.string.tarefa_concluida)
                binding.switchUpdateTarefa.isChecked = true
            }
            false ->
            {
                binding.switchUpdateTarefa.text = getString(R.string.tarefa_a_fazer)
                binding.switchUpdateTarefa.isChecked = false
            }
        }
    }

    private fun btOnClick()
    {
        binding.btUpdateTarefa.setOnClickListener{
            if (binding.editUpdateTarefa.text.isNotBlank() and binding.editUpdateTarefa.text.isNotEmpty())
            {
                viewModel.updateToDo()
                hideKeyboard()
                snack(getString(R.string.tarefa_atualizada))
                Navigation.findNavController(binding.root).popBackStack()
            }
            else
            {
                snack(getString(R.string.warning_digite_uma_tarefa))
            }
        }
    }

    private fun setupObservers()
    {
        viewModel.getLiveData().observe(viewLifecycleOwner, {
                binding.editUpdateTarefa.setText(it.tarefa)
                configSwitch(it.status)
        })
    }

    private fun hideKeyboard()
    {
        val imm = requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
    }

    private fun snack(message: String, duration: Int = Snackbar.LENGTH_LONG)
    {
        Snackbar.make(binding.root,message,duration).show()
    }

}