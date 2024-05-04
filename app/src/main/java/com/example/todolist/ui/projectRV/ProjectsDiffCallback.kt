package com.example.todolist.ui.projectRV

import androidx.recyclerview.widget.DiffUtil
import com.example.todolist.entities.Project

class ProjectsDiffCallback : DiffUtil.ItemCallback<Project>() {

    override fun areItemsTheSame(oldItem: Project, newItem: Project) =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: Project, newItem: Project) =
        oldItem == newItem
}