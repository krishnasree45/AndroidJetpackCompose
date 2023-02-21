package com.example.emptycomposeactivity.viewmodel

import android.app.Application
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emptycomposeactivity.data.WellnessTaskDatabase
import com.example.emptycomposeactivity.model.WellnessTask
import com.example.emptycomposeactivity.repository.WellnessTaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WellnessViewModel(application: Application) : AndroidViewModel(application) {
    /**
     * Don't expose the mutable list of tasks from outside the ViewModel.
     * Instead define _tasks and tasks. _tasks is internal and mutable inside the ViewModel.
     * tasks is public and read-only.
     */

    val readAllData: LiveData<List<WellnessTask>>
    private val repository: WellnessTaskRepository
        init {
        val wellnessTaskDao = WellnessTaskDatabase.getDatabase(application.applicationContext).wellnessTaskDao()
        repository = WellnessTaskRepository(wellnessTaskDao)
        readAllData = repository.readAllData
    }

    private val _tasks = getWellnessTasks().toMutableStateList()
    val tasks: List<WellnessTask>
        get() = _tasks

    fun changeTaskChecked(item: WellnessTask, checked: Boolean) =
        tasks.find { it.id == item.id }?.let { task ->
            task.checked = checked
        }

    fun addWellnessTask(wellnessTask: WellnessTask) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTask(wellnessTask)
        }
    }

    fun removeWellnessTask(wellnessTask: WellnessTask){
        viewModelScope.launch( Dispatchers.IO ) {
            repository.removeTask(wellnessTask)
        }
    }

    fun updateWellnessTask(wellnessTask: WellnessTask){
        viewModelScope.launch( Dispatchers.IO ) {
            repository.updateTask(wellnessTask)
        }
    }

}

private fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }



