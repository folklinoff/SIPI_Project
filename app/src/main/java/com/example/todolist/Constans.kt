package com.example.todolist

import android.net.Uri
import com.example.todolist.entities.MemoryEntity

val AUTHORITY = "com.example.todolist.provider"

val URI_MENU = Uri.parse(
    "content://" + AUTHORITY + "/" + MemoryEntity.TABLE_NAME
)