package com.example.androidtesttool.data

import com.example.androidtesttool.domain.CommandLog
import com.example.androidtesttool.domain.ConnectionState
import com.example.androidtesttool.domain.RobotStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RobotRepository(
    private val client: RobotTcpClient = RobotTcpClient()
) {
    private val _connectionState = MutableStateFlow(ConnectionState.DISCONNECTED)
    val connectionState: StateFlow<ConnectionState> = _connectionState.asStateFlow()

    private val _status = MutableStateFlow(RobotStatus())
    val status: StateFlow<RobotStatus> = _status.asStateFlow()

    private val _logs = MutableStateFlow<List<CommandLog>>(emptyList())
    val logs: StateFlow<List<CommandLog>> = _logs.asStateFlow()

    suspend fun connect(ip: String, port: Int) {
        _connectionState.value = ConnectionState.CONNECTING
        val result = client.connect(ip, port)
        _connectionState.value = if (result.isSuccess) ConnectionState.CONNECTED else ConnectionState.ERROR
        addLog("connect", result.exceptionOrNull()?.message ?: "connected", result.isSuccess)
    }

    suspend fun sendCommand(command: String) {
        val sendResult = client.send(command)
        if (sendResult.isFailure) {
            addLog(command, sendResult.exceptionOrNull()?.message ?: "send error", false)
            _connectionState.value = ConnectionState.ERROR
            return
        }

        val response = client.readLine().getOrElse { "read error: ${it.message}" }
        addLog(command, response ?: "<no response>", true)
    }

    fun disconnect() {
        client.close()
        _connectionState.value = ConnectionState.DISCONNECTED
        addLog("disconnect", "closed", true)
    }

    fun mockUpdateStatus() {
        _status.value = _status.value.copy(
            online = connectionState.value == ConnectionState.CONNECTED,
            batteryPercent = (_status.value.batteryPercent + 3).coerceAtMost(100),
            mode = if (connectionState.value == ConnectionState.CONNECTED) "AUTO" else "IDLE"
        )
    }

    private fun addLog(command: String, response: String, success: Boolean) {
        _logs.value = listOf(
            CommandLog(
                timestamp = System.currentTimeMillis(),
                command = command,
                response = response,
                success = success
            )
        ) + _logs.value
    }
}
