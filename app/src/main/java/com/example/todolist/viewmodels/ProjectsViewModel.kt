package com.example.todolist.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.database.ProjectsRepository
import com.example.todolist.entities.Project
import com.example.todolist.fromProjectsToProjectsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

const val TAG = "ProjectsViewModelClass"

class ProjectsViewModel(val projectsRepository: ProjectsRepository) : ViewModel() {

    /* suspend fun addNewProject(project: Project){
         projectsRepository.addNewProject(fromProjectsToProjectsEntity(project))
     }*/

    private val _projectsListFlow = MutableStateFlow<MutableList<Project>>(arrayListOf())
    val projectsListFlow: StateFlow<MutableList<Project>> = _projectsListFlow.asStateFlow()

    init {

        viewModelScope.launch {
            projectsRepository.getAllProjectsFlow().collect { uit ->
                _projectsListFlow.update {
                    mutableListOf<Project>().apply {
                        addAll(uit.map { noteData ->
                            noteData.copy()
                        })
                    }
                }
            }
        }
    }

    fun initialize() {

    }

    fun deleteNewProject(project: Project) {
        viewModelScope.launch(Dispatchers.IO) {
            projectsRepository.deleteNewProject(fromProjectsToProjectsEntity(project))
        }
    }
}