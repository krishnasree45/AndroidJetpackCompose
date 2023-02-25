package com.example.emptycomposeactivity.view.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emptycomposeactivity.LOGIN_SCREEN
import com.example.emptycomposeactivity.SETTINGS_SCREEN
import com.example.emptycomposeactivity.common.ext.isValidEmail
import com.example.emptycomposeactivity.model.service.LoginService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginService: LoginService,
): ViewModel() {
    var uiState = mutableStateOf(LoginUiState())
        private set

    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password

    fun onEmailEdit(newEmail: String) {
        uiState.value = uiState.value.copy(email = newEmail)
    }

    fun onPasswordEdit(newPwd: String){
        uiState.value = uiState.value.copy(password = newPwd,)
    }

    fun onSignInClick(openAndPopUp: (String, String) -> Unit) {
        if (!email.isValidEmail()) {
            Log.e("LoginViewModel", "Enter valid email")
            return
        }

        if (password.isBlank()) {
            Log.e("LoginViewModel", "Password is blank")
            return
        }

        viewModelScope.launch {
            loginService.authenticate(email, password)
            openAndPopUp(SETTINGS_SCREEN, LOGIN_SCREEN)
        }


//        // Use ViewModelScope.launch
//        launchCatching {
//            accountService.authenticate(email, password)
//            openAndPopUp(SETTINGS_SCREEN, LOGIN_SCREEN)
//        }
    }
}