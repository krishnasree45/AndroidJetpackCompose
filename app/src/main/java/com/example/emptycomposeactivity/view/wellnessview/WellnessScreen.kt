package com.example.emptycomposeactivity.view.wellnessview

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.material.ListItem
import androidx.compose.material.icons.filled.Close
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.emptycomposeactivity.model.WellnessTask
import com.example.emptycomposeactivity.view.watercounter.StatefulCounter
import com.example.emptycomposeactivity.viewmodel.WellnessViewModel

@Composable
fun WellnessScreen(
    modifier: Modifier = Modifier,
    wellnessViewModel: WellnessViewModel = viewModel()
) {
    val items = wellnessViewModel.readAllData.observeAsState(listOf()).value

    Column(modifier = modifier) {
        AddTaskView()
        TaskList(items,wellnessViewModel, onClose = {
            task -> wellnessViewModel.removeWellnessTask(task)
        })
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskList(
    list:List<WellnessTask>,
    wellnessTaskViewModel: WellnessViewModel,
    onClose: (WellnessTask) -> Unit,
){
    val context = LocalContext.current

    LazyColumn() {
        items(list) { task ->
            val name = rememberSaveable { mutableStateOf(task.checked) }

            ListItem(
                text = { Text(text = task.label) },

                trailing = {

                    Row() {
                        Checkbox(
                            checked = name.value,
                            onCheckedChange = {
                                name.value = it
                                task.checked = name.value
                                wellnessTaskViewModel.updateWellnessTask(task)

//                                Toast.makeText(context, "Updated todo!", Toast.LENGTH_SHORT).show()
                            },
                        )

                        IconButton(onClick = { onClose(task) }) {
                            Icon(Icons.Filled.Close, contentDescription = "Close")
                        }
                    }

                },

            )
            Divider()
        }
    }
}