package com.example.todolist.ui.taskRV

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ItemTaskBinding

import com.example.todolist.entities.Task


import java.util.Collections


class TaskRVAdapter() : ListAdapter<Task, TaskProjectsViewHolder>(TaskDiffCallback()),
    TaskItemTouchHelperAdapter {

    var onProjectClickListener: ((Task) -> Unit)? = null
    lateinit var projects: MutableList<Task>
    lateinit var itemTaskBinding: ItemTaskBinding

    fun submit(list:  List<Task>, rv: RecyclerView) {
        projects=list.toMutableList()
        submitList(list){
            rv.invalidateItemDecorations()
        }//иначе добавление нового элемента - проблема
    }

    override fun onItemDismiss(position: Int) {
        projects.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(projects, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(projects, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskProjectsViewHolder {
        itemTaskBinding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TaskProjectsViewHolder(itemTaskBinding)
    }


    override fun onBindViewHolder(holder: TaskProjectsViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item)


    }

}