package com.example.emptycomposeactivity.view.login

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.emptycomposeactivity.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    openAndPopUp: (String, String) -> Unit,
    loginViewModel: LoginViewModel = viewModel(),
    modifier: Modifier = Modifier,
) {

    val uiState by loginViewModel.uiState
    BasicToolbar(title = "Enter your login details")
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Email
        OutlinedTextField(
            value = uiState.email,
            onValueChange = loginViewModel::onEmailEdit,
            Modifier
                .fillMaxWidth()
                .padding(16.dp, 4.dp),
            placeholder = { Text("Email") },
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email") }
        )

        // Password
        OutlinedTextField(
            value = uiState.password,
            onValueChange = loginViewModel::onPasswordEdit,
            Modifier
                .fillMaxWidth()
                .padding(16.dp, 4.dp),
            placeholder = { Text("Password") },
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Lock") },
        )

        //Sign in button
        Button(
            onClick = { loginViewModel.onSignInClick(openAndPopUp) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 8.dp)
        ) {
            Text(text = "Sign in")
        }


    }
}


//
//@Composable
//fun LoginScreen(
//    openAndPopUp: (String, String) -> Unit,
//    modifier: Modifier = Modifier,
//    viewModel: LoginViewModel = viewModel()
//){
//    val uiState by viewModel.uiState
//
//    BasicToolbar("Enter your Login Details")
//
//    Column(
//        modifier = modifier
//            .fillMaxWidth()
//            .fillMaxHeight()
//            .verticalScroll(rememberScrollState()),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        EmailField(uiState.email, viewModel::onEmailChange, Modifier.fieldModifier())
//        PasswordField(uiState.password, viewModel::onPasswordChange, Modifier.fieldModifier())
//
//        BasicButton(AppText.sign_in, Modifier.basicButton()) { viewModel.onSignInClick(openAndPopUp) }
//
//        BasicTextButton(AppText.forgot_password, Modifier.textButton()) {
//            viewModel.onForgotPasswordClick()
//        }
//    }
//}


@Composable
fun BasicToolbar(title: String) {
    TopAppBar(title = { title }, backgroundColor = toolbarColor())
}

@Composable
private fun toolbarColor(darkTheme: Boolean = isSystemInDarkTheme()): Color {
    return if (darkTheme) MaterialTheme.colors.secondary else MaterialTheme.colors.primaryVariant
}
