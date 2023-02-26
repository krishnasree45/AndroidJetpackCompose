package com.example.emptycomposeactivity.model.service

import com.example.emptycomposeactivity.model.WellnessTask
import kotlinx.coroutines.flow.Flow

interface StorageService {

    val tasks: Flow<List<WellnessTask>>

    suspend fun getTask(taskId: String): WellnessTask?

    suspend fun save(task: WellnessTask)
    suspend fun update(task: WellnessTask)
    suspend fun delete(taskId: String)
    suspend fun deleteAllForUser(userId: String)
}