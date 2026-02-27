package com.example.androidtesttool.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androidtesttool.domain.ConnectionState

@Composable
fun ConnectScreen(
    innerPadding: PaddingValues,
    connectionState: ConnectionState,
    onConnect: (String, Int) -> Unit,
    onDisconnect: () -> Unit
) {
    var ip by remember { mutableStateOf("192.168.1.100") }
    var port by remember { mutableIntStateOf(9000) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = ip,
            onValueChange = { ip = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("机器人 IP") }
        )

        OutlinedTextField(
            value = port.toString(),
            onValueChange = { port = it.toIntOrNull() ?: port },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("端口") }
        )

        Text("连接状态：$connectionState")

        Button(onClick = { onConnect(ip, port) }) {
            Text("连接")
        }
        Button(onClick = onDisconnect) {
            Text("断开")
        }
    }
}
