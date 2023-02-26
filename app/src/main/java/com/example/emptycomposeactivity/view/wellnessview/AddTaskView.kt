package com.example.emptycomposeactivity.view.wellnessview

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.emptycomposeactivity.model.WellnessTask
import com.example.emptycomposeactivity.viewmodel.WellnessViewModel

@Composable
fun AddTaskView(wellnessViewModel: WellnessViewModel = viewModel()) {
    val context = LocalContext.current
    var count by rememberSaveable {
        mutableStateOf(0)
    }
    Log.d("AddTaskView", "wellnessViewModel size ${wellnessViewModel.tasks.size}")
    Button(modifier = Modifier.padding(16.dp), onClick = {
        count++
        insertToDB(wellnessViewModel, count)
        Toast.makeText(context, "Added Task", Toast.LENGTH_SHORT).show()
    }) {
        Text(text = "Add Task")
    }
}

private fun insertToDB(wellnessViewModel: WellnessViewModel, count: Int) {
    wellnessViewModel.addWellnessTask(
        WellnessTask(
            count,
            "Task # ${wellnessViewModel.readAllData.value?.size}"
        )
    )

}


@Composable
private fun InputField(
    name: String,
    onValChange: ((String) -> Unit)?
) {
    val focusManager = LocalFocusManager.current

    if (onValChange != null) {
        InputFieldComponent(
            text = name,
            onChange = onValChange,
            label = "Enter todo",
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxWidth()
                .testTag("TEST_INPUT_TAG"),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        )
    }
}

@Composable
fun InputFieldComponent(
    text: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    label: String = "Some val",
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    OutlinedTextField(
        value = text,
        onValueChange = onChange,
        modifier = modifier,
        singleLine = singleLine,
        label = {
            Text(text = label)
        },
        keyboardActions = keyboardActions,
    )
}


