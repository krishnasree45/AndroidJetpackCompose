package com.example.emptycomposeactivity.view.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.emptycomposeactivity.R

@Composable
fun SettingsScreen(
    openScreen: (String) -> Unit,
    restartApp: (String) -> Unit,
    settingsViewModel: SettingsViewModel = hiltViewModel(),
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
        TopAppBar(
            title = { Text("Settings") },
            backgroundColor = MaterialTheme.colors.primary,
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )

        if (uiState.isAnonymousAccount) { // If user is not signed in, display sign in and create account options
            Button(onClick = { settingsViewModel.onLoginClick(openScreen) }) {
                Row() {
                    Image(
                        painter = painterResource(id = R.drawable.ic_sign_in),
                        contentDescription = "Sign In icon"
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(text = "Sign In")
                }
            }

            Button(onClick = { settingsViewModel.onCreateAccountClick(openScreen) }) {
                Row() {
                    Image(
                        painter = painterResource(id = R.drawable.ic_create_account),
                        contentDescription = "Create Account icon"
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(text = "Create Account")
                }
            }

        } else { // If the user is signed in display sign out
            Button(onClick = { settingsViewModel.onSignOutClick(restartApp) }) {
                Row() {
                    Image(
                        painter = painterResource(id = R.drawable.ic_exit),
                        contentDescription = "Sign out icon"
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "Sign Out")
                }
            }

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp))

            Button(onClick = { settingsViewModel.onDeleteMyAccountClick(restartApp) }) {
                Row() {
                    Image(
                        painter = painterResource(id = R.drawable.ic_delete_my_account),
                        contentDescription = "Delete Account"
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "Delete Account")
                }
            }
        }
    }


}