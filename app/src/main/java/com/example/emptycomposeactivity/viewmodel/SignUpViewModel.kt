package com.example.emptycomposeactivity.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.emptycomposeactivity.common.ext.isValidEmail
import com.example.emptycomposeactivity.common.ext.isValidPassword
import com.example.emptycomposeactivity.common.ext.passwordMatches
import com.example.emptycomposeactivity.view.signup.SignUpUiState

class SignUpViewModel : ViewModel() {

    var uiState = mutableStateOf(SignUpUiState())
        private set

    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onRepeatPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(repeatPassword = newValue)
    }

    fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {
        if (!email.isValidEmail()) {
            Log.e("SignUpViewModel", "Enter valid email")
            return
        }

        if (!password.isValidPassword()) {
            Log.e("SignUpViewModel", "Password is blank")
            return
        }

        if (!password.passwordMatches(uiState.value.repeatPassword)) {
            Log.e("SignUpViewModel", "Passwords should match")
            return
        }

//        launchCatching {
//            accountService.linkAccount(email, password)
//            openAndPopUp(SETTINGS_SCREEN, SIGN_UP_SCREEN)
//        }
    }
}