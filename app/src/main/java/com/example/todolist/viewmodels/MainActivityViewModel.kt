package com.example.todolist.viewmodels

import androidx.lifecycle.ViewModel
import com.example.todolist.database.ProjectsRepository
import com.example.todolist.entities.Project
import com.example.todolist.fromProjectsToProjectsEntity

class MainActivityViewModel(val projectsRepository: ProjectsRepository) : ViewModel()  {

     suspend fun addNewProject(project: Project){
        projectsRepository.addNewProject(fromProjectsToProjectsEntity(project))
    }
}