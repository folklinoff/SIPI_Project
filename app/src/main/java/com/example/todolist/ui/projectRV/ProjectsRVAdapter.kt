package com.example.todolist.ui.projectRV

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ItemProjectBinding
import com.example.todolist.entities.Project
import java.util.Collections


class ProjectsRVAdapter() : ListAdapter<Project, ProjectsViewHolder>(ProjectsDiffCallback()),
    ProjectsItemTouchHelperAdapter {
    var onProjectSwipeListener: ((Project) -> Unit)?=null
    var onProjectClickListener: ((Project) -> Unit)? = null
    lateinit var projects: MutableList<Project>
    lateinit var itemProjectBinding: ItemProjectBinding

    fun submit(list:  List<Project>, rv: RecyclerView) {
        projects=list.toMutableList()
        submitList(projects){
            rv.invalidateItemDecorations()
        }//иначе добавление нового элемента - проблема
    }

    override fun onItemDismiss(position: Int) {
        onProjectSwipeListener?.invoke(projects[position])
        notifyItemRemoved(position)
        projects.removeAt(position)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectsViewHolder {
        itemProjectBinding = ItemProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProjectsViewHolder(itemProjectBinding)
    }


    override fun onBindViewHolder(holder: ProjectsViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item)
        with(holder.binding) {
            containerProject.setOnClickListener() {
                onProjectClickListener?.invoke(item)
            }
        }

    }

}