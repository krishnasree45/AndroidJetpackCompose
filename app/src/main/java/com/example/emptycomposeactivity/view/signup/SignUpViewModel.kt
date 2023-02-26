package com.example.emptycomposeactivity.view.signup

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emptycomposeactivity.SETTINGS_SCREEN
import com.example.emptycomposeactivity.SIGN_UP_SCREEN
import com.example.emptycomposeactivity.common.ext.*
import com.example.emptycomposeactivity.model.service.LoginService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val loginService: LoginService,
) : ViewModel() {

    var uiState = mutableStateOf(SignUpUiState())
        private set
    var status = MutableLiveData(STATUS_OK)

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
            status.value = NOT_VALID_EMAIL
            return
        }

        if (!password.isValidPassword()) {
            Log.e("SignUpViewModel", "Password is blank")
            status.value = NOT_VALID_PWD
            return
        }

        if (!password.passwordMatches(uiState.value.repeatPassword)) {
            Log.e("SignUpViewModel", "Passwords should match")
            status.value = PWD_DOESNT_MATCH
            return
        }

        viewModelScope.launch() {
            loginService.linkAccount(email, password)
            openAndPopUp(SETTINGS_SCREEN, SIGN_UP_SCREEN)
        }
    }
}