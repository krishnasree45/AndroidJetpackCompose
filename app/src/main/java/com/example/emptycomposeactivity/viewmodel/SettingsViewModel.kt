package com.example.emptycomposeactivity.viewmodel

import androidx.lifecycle.ViewModel
import com.example.emptycomposeactivity.SETTINGS_SCREEN
import com.example.emptycomposeactivity.model.service.LoginService
import com.example.emptycomposeactivity.view.settings.SettingsUiState
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val loginService: LoginService,

    ) : ViewModel() {
    val uiState = loginService.currentUser.map { SettingsUiState(it.isAnonymous) }

    fun onLoginClick(openScreen: (String) -> Unit) {
        openScreen(SETTINGS_SCREEN)
    }

    fun onCreateAccountClick(openScreen: (String) -> Unit) {
        openScreen(SETTINGS_SCREEN)
    }


}