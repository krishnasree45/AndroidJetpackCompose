package com.example.emptycomposeactivity.view.edittask

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.emptycomposeactivity.R

@Composable
fun EditTaskScreen(
    popUpScreen: () -> Unit,
    modifier: Modifier = Modifier,
    editTaskScreenViewModel: EditTaskViewModel = hiltViewModel()
) {
    val task by editTaskScreenViewModel.task


    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(
            title = { Text("Edit Task") },
            backgroundColor = MaterialTheme.colors.primary,
            actions = {
                Box(
                    Modifier.wrapContentSize(Alignment.TopEnd)
                ) {
                    IconButton(onClick = {
                        Log.d("EditTaskScreen", "Clicked done")
                        editTaskScreenViewModel.onDoneClick(popUpScreen)
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_check),
                            contentDescription = "Action"
                        )
                    }
                }
            }
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        )

        OutlinedTextField(
            singleLine = true,
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp, 4.dp),
            value = task.label,
            onValueChange = editTaskScreenViewModel::onTitleChange,
            placeholder = { Text("Title") }
        )

        OutlinedTextField(
            singleLine = true,
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp, 4.dp),
            value = task.description,
            onValueChange = editTaskScreenViewModel::onDescriptionChange,
            placeholder = { Text("Description") }
        )

    }
}