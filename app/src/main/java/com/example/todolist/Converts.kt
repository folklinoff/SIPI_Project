package com.example.todolist

import androidx.room.TypeConverter
import com.example.todolist.entities.Project
import com.example.todolist.entities.ProjectEntity
import java.util.Date



fun fromProjectsToProjectsEntity(project: Project):ProjectEntity {
    val inputDate = project.date
   return ProjectEntity(
        id = project.id,
        name = project.name,
        date = inputDate?.time ,
        isCompleted = project.isCompleted
    )

}


fun fromProjectsEntityToProjects(projectEntity: ProjectEntity):Project {
    val inputDate = projectEntity.date
    return Project(
        id = projectEntity.id,
        name = projectEntity.name,
        date =  inputDate?.let { Date(it) },
        isCompleted = projectEntity.isCompleted
    )

}