package com.example.emptycomposeactivity.view.signup

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.emptycomposeactivity.view.login.BasicToolbar
import com.example.emptycomposeactivity.viewmodel.SignUpViewModel

@Composable
fun SignUpScreen(
    openAndPopUp: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    signUpViewModel: SignUpViewModel = viewModel()
) {
    val uiState by signUpViewModel.uiState
    BasicToolbar(title = "Create Account")

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
            value = "Email",
            onValueChange = signUpViewModel::onEmailChange,
            Modifier
                .fillMaxWidth()
                .padding(16.dp, 4.dp),
            placeholder = { Text("Email") },
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email") }
        )

        //Password
        OutlinedTextField(
            value = "Password",
            onValueChange = signUpViewModel::onPasswordChange,
            Modifier
                .fillMaxWidth()
                .padding(16.dp, 4.dp),
            placeholder = { Text("Password") },
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Lock") },
        )

        //Repeat Password
        OutlinedTextField(
            value = "Password",
            onValueChange = signUpViewModel::onPasswordChange,
            Modifier
                .fillMaxWidth()
                .padding(16.dp, 4.dp),
            placeholder = { Text("Repeat Password") },
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