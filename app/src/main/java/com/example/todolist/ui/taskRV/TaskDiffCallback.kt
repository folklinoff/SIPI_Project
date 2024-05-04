package com.example.todolist.ui.taskRV

import androidx.recyclerview.widget.DiffUtil
import com.example.todolist.entities.Task

class TaskDiffCallback : DiffUtil.ItemCallback<Task>() {

    override fun areItemsTheSame(oldItem: Task, newItem: Task) =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: Task, newItem: Task) =
        oldItem == newItem
}