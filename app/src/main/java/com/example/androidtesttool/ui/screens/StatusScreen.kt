package com.example.androidtesttool.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androidtesttool.domain.RobotStatus

@Composable
fun StatusScreen(
    innerPadding: PaddingValues,
    status: RobotStatus
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("在线：${status.online}")
        Text("模式：${status.mode}")
        Text("电量：${status.batteryPercent}%")
        Text("故障码：${status.faultCode ?: "无"}")
        status.joints.forEachIndexed { index, angle ->
            Text("关节${index + 1}: $angle°")
        }
    }
}
