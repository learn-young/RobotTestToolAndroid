package com.example.androidtesttool.ui.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.androidtesttool.ui.screens.ConnectScreen
import com.example.androidtesttool.ui.screens.ControlScreen
import com.example.androidtesttool.ui.screens.LogScreen
import com.example.androidtesttool.ui.screens.StatusScreen
import com.example.androidtesttool.ui.screens.TopTabBar
import com.example.androidtesttool.ui.viewmodel.MainViewModel

object Routes {
    const val Connect = "connect"
    const val Control = "control"
    const val Status = "status"
    const val Logs = "logs"
}

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel()
) {
    val navController = rememberNavController()
    val connectionState by viewModel.connectionState.collectAsStateWithLifecycle()
    val robotStatus by viewModel.robotStatus.collectAsStateWithLifecycle()
    val logs by viewModel.logs.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
            TopTabBar(currentRoute = currentRoute) { route ->
                navController.navigate(route) {
                    launchSingleTop = true
                }
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.Connect,
            modifier = modifier
        ) {
            composable(Routes.Connect) {
                ConnectScreen(
                    innerPadding = innerPadding,
                    connectionState = connectionState,
                    onConnect = viewModel::connect,
                    onDisconnect = viewModel::disconnect
                )
            }
            composable(Routes.Control) {
                ControlScreen(
                    innerPadding = innerPadding,
                    onSendCommand = viewModel::sendCommand
                )
            }
            composable(Routes.Status) {
                StatusScreen(
                    innerPadding = innerPadding,
                    status = robotStatus
                )
            }
            composable(Routes.Logs) {
                LogScreen(
                    innerPadding = innerPadding,
                    logs = logs
                )
            }
        }
    }
}
