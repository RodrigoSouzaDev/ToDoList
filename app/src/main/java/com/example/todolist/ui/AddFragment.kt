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
import com.example.todolist.databinding.FragmentAddBinding
import com.example.todolist.viewmodel.AddFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject


class AddFragment : Fragment()
{
    private val viewModel : AddFragmentViewModel by inject()
    private lateinit var binding: FragmentAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        onEditTextChanged()
        onClickButton()
        return binding.root
    }

    private fun onEditTextChanged()
    {
        binding.editAddtarefa.doAfterTextChanged {
            viewModel.setToDo(binding.editAddtarefa.text.toString())
        }
    }

    private fun onClickButton()
    {
        binding.btAddtarefa.setOnClickListener{
            if (binding.editAddtarefa.text.isNotBlank() and binding.editAddtarefa.text.isNotEmpty())
            {
                viewModel.insertTodo()
                hideKeyboard()
                snack(getString(R.string.tarefa_adicionada))
                Navigation.findNavController(binding.root).popBackStack()
            }
            else
            {
                snack(getString(R.string.warning_digite_uma_tarefa))
            }
        }

    }

    private fun hideKeyboard() {
        val imm = requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)

    }

    private fun snack(message: String, duration: Int = Snackbar.LENGTH_LONG) {
        Snackbar.make(binding.root,message,duration).show()
    }
}