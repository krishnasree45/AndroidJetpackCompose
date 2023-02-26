package com.example.emptycomposeactivity.view.wellnessview

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.emptycomposeactivity.R
import com.example.emptycomposeactivity.model.WellnessTask

@Composable
fun WellnessScreen(
    modifier: Modifier = Modifier,
    wellnessViewModel: WellnessViewModel = hiltViewModel(),
    openScreen: (String) -> Unit,
) {

    Column() {
        val tasks = wellnessViewModel.tasks.collectAsStateWithLifecycle(emptyList())

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            TopAppBar(
                title = { Text("Tasks") },
                modifier = Modifier.wrapContentSize(Alignment.TopEnd),
                actions = {
                    Box(modifier) {
                        IconButton(onClick = { wellnessViewModel.onSettingsClick(openScreen) }) {
                            Icon(
                                painter = painterResource(R.drawable.ic_settings),
                                contentDescription = "Settings"
                            )
                        }
                    }
                }
            )
            Spacer(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Button(
                onClick = { wellnessViewModel.onAddClick(openScreen) },
                modifier = Modifier.padding(all = 8.dp)
            ) {
                Text(text = "Add Task")
            }


            Spacer(
                modifier = modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )

            LazyColumn {
                items(tasks.value, key = { it.id }) { taskItem ->
                    TaskItem(
                        wellnessTask = taskItem,
                        wellnessTaskViewModel = wellnessViewModel,
                    )
                }
            }

        }

    }
}

@Composable
fun TaskItem(
    wellnessTaskViewModel: WellnessViewModel,
    wellnessTask: WellnessTask,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Checkbox(checked = wellnessTask.checked,
            onCheckedChange = { wellnessTaskViewModel.onTaskCheckChange(wellnessTask) })

        Text(text = wellnessTask.label)

        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = { wellnessTaskViewModel.onDeleteTask(wellnessTask) }) {
            Icon(Icons.Filled.Close, contentDescription = "Close task")
        }

    }
}