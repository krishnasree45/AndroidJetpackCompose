package com.example.emptycomposeactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
//            EmptyComposeActivityTheme() {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background,
//                ) {
//                    WellnessScreen()
//                }
//            }
            LoginApp()

        }

    }
}
