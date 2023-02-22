package com.example.emptycomposeactivity.view.wellnessview

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.emptycomposeactivity.LOGIN_SCREEN
import com.example.emptycomposeactivity.R
import com.example.emptycomposeactivity.SIGN_UP_SCREEN
import com.example.emptycomposeactivity.WELLNESS_SCREEN
import com.example.emptycomposeactivity.model.WellnessTask
import com.example.emptycomposeactivity.view.watercounter.StatefulCounter
import com.example.emptycomposeactivity.viewmodel.WellnessViewModel

@Composable
fun WellnessScreen(
    modifier: Modifier = Modifier,
    wellnessViewModel: WellnessViewModel = viewModel(),
    openScreen: (String) -> Unit,
) {
    val items = wellnessViewModel.readAllData.observeAsState(listOf()).value

    Column(modifier = modifier) {
        StatefulCounter()
        Row() {
            AddTaskView()
            //Settings Button
            Button(
                onClick = {
                    Log.d("WellnessScreen", "Button clicked")
                    openScreen(LOGIN_SCREEN)
//                    wellnessViewModel.onSettingsClick(openScreen)
                          },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_settings),
                    contentDescription = "Settings Button"
                )
            }
        }

        // List of tasks
        TaskList(items, wellnessViewModel, onClose = { task ->
            wellnessViewModel.removeWellnessTask(task)
        })
//        WellnessTasksList(
//            list = wellnessViewModel.tasks,
//            onCheckedTask = { task, checked ->
//                wellnessViewModel.changeTaskChecked(task, checked)
//            },
//            onCloseTask = { task ->
//                wellnessViewModel.removeWellnessTask(task)
//            }
//        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskList(
    list: List<WellnessTask>,
    wellnessTaskViewModel: WellnessViewModel,
    onClose: (WellnessTask) -> Unit,
) {
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

@Composable
private fun toolbarColor(darkTheme: Boolean = isSystemInDarkTheme()): Color {
    return if (darkTheme) MaterialTheme.colors.secondary else MaterialTheme.colors.primaryVariant
}