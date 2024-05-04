package com.example.todolist.ui.projectRV

import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.entities.Project
import com.example.todolist.databinding.ItemProjectBinding
import java.text.DateFormat
import java.util.Date

class ProjectsViewHolder(val binding: ItemProjectBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: Project) {
        with(binding) {
    textViewName.text = item.name
    textViewDate.text =  DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(
        item.date!!.time)

        }
    }
}