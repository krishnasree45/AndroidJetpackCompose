package com.example.emptycomposeactivity.view.signup

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emptycomposeactivity.SETTINGS_SCREEN
import com.example.emptycomposeactivity.SIGN_UP_SCREEN
import com.example.emptycomposeactivity.common.ext.isValidEmail
import com.example.emptycomposeactivity.common.ext.isValidPassword
import com.example.emptycomposeactivity.common.ext.passwordMatches
import com.example.emptycomposeactivity.model.service.LoginService
import com.example.emptycomposeactivity.model.service.StorageService
import com.example.emptycomposeactivity.view.signup.SignUpUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val loginService: LoginService,
) : ViewModel() {

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

        viewModelScope.launch() {
            loginService.linkAccount(email, password)
            openAndPopUp(SETTINGS_SCREEN, SIGN_UP_SCREEN)
        }
    }
}