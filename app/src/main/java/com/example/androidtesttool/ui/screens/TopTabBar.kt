package com.example.androidtesttool.ui.screens

import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.androidtesttool.ui.navigation.Routes

@Composable
fun TopTabBar(currentRoute: String?, onNavigate: (String) -> Unit) {
    val tabs = listOf(
        Routes.Connect to "连接",
        Routes.Control to "控制",
        Routes.Status to "状态",
        Routes.Logs to "日志"
    )

    val selectedIndex = tabs.indexOfFirst { it.first == currentRoute }.coerceAtLeast(0)

    TabRow(selectedTabIndex = selectedIndex) {
        tabs.forEach { (route, title) ->
            Tab(
                selected = currentRoute == route,
                onClick = { onNavigate(route) },
                text = { Text(title) }
            )
        }
    }
}
