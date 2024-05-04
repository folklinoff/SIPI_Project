package com.example.todolist.entities

import java.time.LocalDate
import java.util.Date

data class Project (
    val id: String,
    val name:String,
    val date: Date?,
    var isCompleted: Boolean
         )