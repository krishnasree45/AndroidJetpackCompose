package com.example.emptycomposeactivity.view.wellnessview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emptycomposeactivity.EDIT_TASK_SCREEN
import com.example.emptycomposeactivity.SETTINGS_SCREEN
import com.example.emptycomposeactivity.model.WellnessTask
import com.example.emptycomposeactivity.model.service.StorageService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WellnessViewModel @Inject constructor(
    private val storageService: StorageService,
) : ViewModel() {

    val tasks = storageService.tasks

    fun onTaskCheckChange(task: WellnessTask) {
        viewModelScope.launch { storageService.update(task.copy(checked = !task.checked)) }
    }

    fun onAddClick(openScreen: (String) -> Unit) = openScreen(EDIT_TASK_SCREEN)

    fun onSettingsClick(openScreen: (String) -> Unit) = openScreen(SETTINGS_SCREEN)

    fun onDeleteTask(task: WellnessTask) {
        viewModelScope.launch { storageService.delete(taskId = task.id) }
    }


}