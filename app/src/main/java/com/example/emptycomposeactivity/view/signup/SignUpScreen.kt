package com.example.emptycomposeactivity.view.signup

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.emptycomposeactivity.common.ext.NOT_VALID_EMAIL
import com.example.emptycomposeactivity.common.ext.NOT_VALID_PWD
import com.example.emptycomposeactivity.common.ext.PWD_DOESNT_MATCH
import com.example.emptycomposeactivity.view.login.BasicToolbar

@Composable
fun SignUpScreen(
    openAndPopUp: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    signUpViewModel: SignUpViewModel = hiltViewModel()
) {
    val uiState by signUpViewModel.uiState
    BasicToolbar(title = "Create Account")
    val status by signUpViewModel.status.observeAsState()

    when (status) {
        NOT_VALID_PWD -> {
            Toast.makeText(
                LocalContext.current,
                "Error: Password is not valid!",
                Toast.LENGTH_SHORT
            ).show()
        }
        PWD_DOESNT_MATCH -> {
            Toast.makeText(
                LocalContext.current,
                "Error: Passwords do not match!",
                Toast.LENGTH_SHORT
            ).show()
        }
        NOT_VALID_EMAIL -> {
            Toast.makeText(LocalContext.current, "Error: Email is not valid!", Toast.LENGTH_SHORT)
                .show()
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Email
        OutlinedTextField(
            value = uiState.email,
            onValueChange = signUpViewModel::onEmailChange,
            Modifier
                .fillMaxWidth()
                .padding(16.dp, 4.dp),
            placeholder = { Text("Email") },
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email") }
        )

        //Password
        OutlinedTextField(
            value = uiState.password,
            onValueChange = signUpViewModel::onPasswordChange,
            Modifier
                .fillMaxWidth()
                .padding(16.dp, 4.dp),
            placeholder = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Lock") },
        )

        //Repeat Password
        OutlinedTextField(
            value = uiState.repeatPassword,
            onValueChange = signUpViewModel::onRepeatPasswordChange,
            Modifier
                .fillMaxWidth()
                .padding(16.dp, 4.dp),
            placeholder = { Text("Repeat Password") },
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Lock") },

            )

        //Create Account button
        Button(
            onClick = { signUpViewModel.onSignUpClick(openAndPopUp) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 8.dp)
        ) {
            Text(text = "Create Account")
        }
    }

}