package com.example.emptycomposeactivity.view.settings

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.emptycomposeactivity.view.login.BasicToolbar
import com.example.emptycomposeactivity.viewmodel.SettingsViewModel
import com.example.emptycomposeactivity.R
import javax.inject.Inject

@Composable
fun SettingsScreen (
    openScreen: (String) -> Unit,
    settingsViewModel: SettingsViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val uiState by settingsViewModel.uiState.collectAsState(
        initial = SettingsUiState(
            isAnonymousAccount = false
        )
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        BasicToolbar(title = "Settings")

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        )

        if (uiState.isAnonymousAccount) { // If user is not signed in, display sign in and create account options
            Button(onClick = { settingsViewModel.onLoginClick(openScreen) }) {
                Row() {
                    Image(
                        painter = painterResource(id = R.drawable.ic_sign_in),
                        contentDescription = "Sign In icon"
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "Sign In")
                }
            }

            Button(onClick = { settingsViewModel.onCreateAccountClick(openScreen) }) {
                Row() {
                    Image(
                        painter = painterResource(id = R.drawable.ic_create_account),
                        contentDescription = "Create Account icon"
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "Create Account")
                }
            }


        } else { // If the user is signed in display sign out
            Button(onClick = { /*TODO call settingsViewModel.createAccount()*/ }) {
                Row() {
                    Image(
                        painter = painterResource(id = R.drawable.ic_exit),
                        contentDescription = "Sign out icon"
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "Sign Out")
                }
            }
        }
    }


}