package com.example.todolist.database

import com.example.todolist.entities.Project
import com.example.todolist.entities.ProjectEntity
import com.example.todolist.fromProjectsEntityToProjects
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class ProjectsRepository(val dao: ProjectsDao) {
    suspend fun getAllProjectsFlow(): Flow<List<Project>> {
        val list = dao.getAllProjectsFlowEntity()
        return list.map {
            it.map { uit ->
                fromProjectsEntityToProjects(uit)
            }
        }
    }


    suspend fun addNewProject(projectEntity: ProjectEntity) {
        withContext(Dispatchers.IO) {
            dao.insertNewProject(projectEntity)
        }
    }

    suspend fun deleteNewProject(projectEntity: ProjectEntity) {
        withContext(Dispatchers.IO) {
            dao.deleteNewProject(projectEntity)
        }
    }
}