package com.example.emptycomposeactivity.view.firstscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emptycomposeactivity.FIRST_SCREEN
import com.example.emptycomposeactivity.WELLNESS_SCREEN
import com.example.emptycomposeactivity.model.service.LoginService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FirstScreenViewModel @Inject constructor(
    private val loginService: LoginService,
) : ViewModel() {


    fun onAppStart(openAndPopUp: (String, String) -> Unit) {

        if (loginService.hasUser) openAndPopUp(WELLNESS_SCREEN, FIRST_SCREEN)
        else createAnonymousAccount(openAndPopUp)
    }

    private fun createAnonymousAccount(openAndPopUp: (String, String) -> Unit) {
        viewModelScope.launch {
            loginService.createAnonymousAccount()
            openAndPopUp(WELLNESS_SCREEN, FIRST_SCREEN)
        }
    }

}