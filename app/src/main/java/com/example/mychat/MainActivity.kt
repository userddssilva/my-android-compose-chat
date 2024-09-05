package com.example.mychat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.mychat.navigation.Screen
import com.example.mychat.navigation.SetupNavGraph
import com.example.mychat.ui.theme.MyChatTheme
import com.parse.ParseUser

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyChatTheme {
                SetupNavGraph(
                    navController = rememberNavController(),
                    startDestination = if (ParseUser.getCurrentUser() != null) Screen.Home else Screen.Auth
                )
            }
        }
    }
}