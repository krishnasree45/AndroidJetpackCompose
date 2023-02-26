package com.example.emptycomposeactivity.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.emptycomposeactivity.model.WellnessTask

@Dao
interface WellnessTaskDao {

    @Query("SELECT * from wellnessTasks")
    fun getAll(): LiveData<List<WellnessTask>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(wellnessTask: WellnessTask)

    @Query("DELETE FROM wellnessTasks WHERE wellnessTaskId = :id")
    fun removeTask(id: Int)

    @Update
    suspend fun updateTask(task: WellnessTask)

}