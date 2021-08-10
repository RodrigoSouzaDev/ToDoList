package com.example.todolist.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ItemRecyclerBinding
import com.example.todolist.model.ToDo

class ToDoAdapter(
    val onBtDeleteClicked: (ToDo) -> Unit,
    val onLayoutClicked: (Int) -> Unit,
    val onCBoxClicked: (ToDo) -> Unit
    ): RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>()
{
    private var toDos : ArrayList<ToDo> = ArrayList()

    class ToDoViewHolder(val binding: ItemRecyclerBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val todo = toDos[position]
        configTvFlag(holder,todo.status)
        holder.binding.itemTv.text = todo.tarefa
        holder.binding.itemCheckBox.isChecked = todo.status
        holder.binding.itemImageButton.setOnClickListener{ onBtDeleteClicked(todo)}
        holder.binding.itemLayout.setOnClickListener{onLayoutClicked(todo.id)}
        holder.binding.itemCheckBox.setOnClickListener{
            todo.status = holder.binding.itemCheckBox.isChecked
            onCBoxClicked(todo)
        }
    }

    override fun getItemCount(): Int
    {
        return toDos.size
    }

    fun updateArray(array: ArrayList<ToDo>)
    {
        toDos = array
    }

    private fun configTvFlag(holder: ToDoViewHolder, bool: Boolean)
    {
        when (bool)
        {
            true ->
            {
                holder.binding.itemTv.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            }
            false ->
            {
                holder.binding.itemTv.paintFlags = Paint.ANTI_ALIAS_FLAG
            }
        }
    }
}
