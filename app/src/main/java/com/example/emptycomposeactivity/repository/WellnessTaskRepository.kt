package com.example.emptycomposeactivity.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.emptycomposeactivity.data.WellnessTaskDao
import com.example.emptycomposeactivity.model.WellnessTask

class WellnessTaskRepository(private val wellnessTaskDao: WellnessTaskDao) {
    val readAllData : LiveData<List<WellnessTask>> =  wellnessTaskDao.getAll()

    suspend fun addTask(task: WellnessTask){
        Log.d("WellnessTaskRepo", "Inserting task")
        wellnessTaskDao.insertTask(task)
    }

    suspend fun removeTask(task:WellnessTask){
        wellnessTaskDao.removeTask(task.id)
    }

    suspend fun updateTask(task:WellnessTask){
        wellnessTaskDao.updateTask(task)
    }
}