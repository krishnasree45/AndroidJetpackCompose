package com.example.emptycomposeactivity

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.emptycomposeactivity.ui.theme.EmptyComposeActivityTheme
import com.example.emptycomposeactivity.view.edittask.EditTaskScreen
import com.example.emptycomposeactivity.view.firstscreen.FirstScreen
import com.example.emptycomposeactivity.view.login.LoginScreen
import com.example.emptycomposeactivity.view.settings.SettingsScreen
import com.example.emptycomposeactivity.view.signup.SignUpScreen
import com.example.emptycomposeactivity.view.wellnessview.WellnessScreen

@Composable
fun LoginApp() {
    EmptyComposeActivityTheme {
        val appState = rememberAppState()
        Surface(color = MaterialTheme.colors.background) {
            Scaffold() { innerPaddingModifier ->
                NavHost(
                    navController = appState.navController,
                    startDestination = FIRST_SCREEN,
                    modifier = Modifier.padding(innerPaddingModifier)
                ) {
                    LoginAppGraph(appState)
                }
            }
        }
    }
}

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
) = remember(navController) {
    LoginAppState(navController)
}


fun NavGraphBuilder.LoginAppGraph(appState: LoginAppState) {

    composable(FIRST_SCREEN) {
        FirstScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(EDIT_TASK_SCREEN) {
        EditTaskScreen(popUpScreen = { appState.popUp() })
    }

    composable(WELLNESS_SCREEN) {
        WellnessScreen(openScreen = { route -> appState.navigate(route) })
    }

    composable(LOGIN_SCREEN) {
        LoginScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(SIGN_UP_SCREEN) {
        SignUpScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(SETTINGS_SCREEN) {
        SettingsScreen(
            openScreen = { route -> appState.navigate(route) },
            restartApp = { route -> appState.clearAndNavigate(route)}
        )
    }
}