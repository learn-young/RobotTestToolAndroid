package com.example.androidtesttool.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ControlScreen(
    innerPadding: PaddingValues,
    onSendCommand: (String) -> Unit
) {
    val commands = listOf("get_status", "base_move", "arm_move", "arm_home", "stop", "estop")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text("测试指令")
        commands.forEach { cmd ->
            Button(onClick = { onSendCommand(cmd) }) {
                Text("发送：$cmd")
            }
        }
    }
}
