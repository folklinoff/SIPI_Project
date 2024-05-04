package com.example.todolist.database

import android.app.Application
import android.util.Log
import com.example.todolist.provider.MemoriesContentProvider

const val TAG="AppClass"
class App:Application() {
    private val projectsDatabase by lazy{ProjectsDB.getDatabase(applicationContext)}
    val projectsRepository by lazy{
        ProjectsRepository(projectsDatabase.getProjectDao())
    }.also {  Log.d(TAG, " again!") }

    val daoMemory by lazy{
        projectsDatabase.getMemoryDao()
    }

    val memoryContentProvider by lazy{
       // MemoriesContentProvider()
    }
     //lateinit var memoryContentProvider:MemoriesContentProvider
    override fun onCreate() {
        super.onCreate()
       // memoryContentProvider =  MemoriesContentProvider()
        //Log.d("important", "onCreateInsertApp: " + memoryContentProvider)

    }

}