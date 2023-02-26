package com.example.emptycomposeactivity.view.edittask

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emptycomposeactivity.model.WellnessTask
import com.example.emptycomposeactivity.model.service.StorageService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditTaskViewModel @Inject constructor(
    private val storageService: StorageService
) : ViewModel() {
    val task = mutableStateOf(WellnessTask())

    fun onDoneClick(popUpScreen: () -> Unit,) {
        Log.d("EditTaskViewModel", "onDoneClick")
        viewModelScope.launch (
             block = {
                val editedTask = task.value
                Log.d("EditTaskViewModel", "Title of editedTask: ${editedTask.label}")
                Log.d("EditTaskViewModel", "Id of editedTask: ${editedTask.id}")
                if (editedTask.id.isBlank()) {
                    Log.d("EditTaskViewModel", "editedTaskId is blank")
                    storageService.save(editedTask)
                } else {
                    Log.d("EditTaskViewModel", "editedTaskId is blank")
                    storageService.update(editedTask)
                }
                Log.d("EditTaskViewModel", "Before popUpScreen()")
                popUpScreen()
            }
        )
    }

    fun onTitleChange(newValue: String) {
        task.value = task.value.copy(label = newValue)
    }

    fun onDescriptionChange(newValue: String) {
        task.value = task.value.copy(description = newValue)
    }

}