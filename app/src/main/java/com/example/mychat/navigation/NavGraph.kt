package com.example.mychat.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mychat.presentation.screen.auth.AuthScreen
import com.example.mychat.presentation.screen.home.HomeScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: Screen = Screen.Auth
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Screen.Auth> {
            AuthScreen(
                onAuthenticated = {
                    navController.popBackStack()
                    navController.navigate(Screen.Home)
                }
            )
        }
        composable<Screen.Home> {
            HomeScreen(
                onLogout = {
                    navController.popBackStack()
                    navController.navigate(Screen.Auth)
                },
                onCreateRoom = {
                    navController.navigate(Screen.CreateRoom)
                },
                onChatRoomSelect = {
                    navController.navigate(Screen.Chat(id = it))
                }
            )
        }
        composable<Screen.CreateRoom> {}
        composable<Screen.Chat> {}

    }
}