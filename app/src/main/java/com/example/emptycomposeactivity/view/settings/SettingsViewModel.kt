package com.example.emptycomposeactivity.view.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emptycomposeactivity.FIRST_SCREEN
import com.example.emptycomposeactivity.LOGIN_SCREEN
import com.example.emptycomposeactivity.SETTINGS_SCREEN
import com.example.emptycomposeactivity.SIGN_UP_SCREEN
import com.example.emptycomposeactivity.model.service.LoginService
import com.example.emptycomposeactivity.model.service.StorageService
import com.example.emptycomposeactivity.view.settings.SettingsUiState
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
            restartApp(FIRST_SCREEN) // TODO - Create splash screen
        }
    }

    fun onDeleteMyAccountClick(restartApp: (String) -> Unit) {
        viewModelScope.launch {
            storageService.deleteAllForUser(loginService.currentUserId)
//            loginService.deleteAccount() // TODO - Add delete account option
//            restartApp(SPLASH_SCREEN) // TODO - Create splash screen
        }
    }


}