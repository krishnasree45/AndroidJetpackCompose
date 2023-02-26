package com.example.emptycomposeactivity.view.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emptycomposeactivity.FIRST_SCREEN
import com.example.emptycomposeactivity.LOGIN_SCREEN
import com.example.emptycomposeactivity.SIGN_UP_SCREEN
import com.example.emptycomposeactivity.model.service.LoginService
import com.example.emptycomposeactivity.model.service.StorageService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val loginService: LoginService,
    private val storageService: StorageService,
) : ViewModel() {
    val uiState = loginService.currentUser.map { SettingsUiState(it.isAnonymous) }

    fun onLoginClick(openScreen: (String) -> Unit) {
        openScreen(LOGIN_SCREEN)
    }

    fun onCreateAccountClick(openScreen: (String) -> Unit) {
        openScreen(SIGN_UP_SCREEN)
    }

    fun onSignOutClick(restartApp: (String) -> Unit) {
        viewModelScope.launch {
            loginService.signOut()
            restartApp(FIRST_SCREEN)
        }
    }

    fun onDeleteMyAccountClick(restartApp: (String) -> Unit) {
        Log.d("SettingsViewModel", "onDeleteMyAccountClick")
        viewModelScope.launch(
            block = {
                loginService.deleteAccount()
                Log.d("SettingsViewModel", "Before Restart app")
                restartApp(FIRST_SCREEN)
            }
        )
    }

}