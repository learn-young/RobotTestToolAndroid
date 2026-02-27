package com.example.androidtesttool.domain

enum class ConnectionState {
    DISCONNECTED,
    CONNECTING,
    CONNECTED,
    RECONNECTING,
    ERROR
}

data class RobotEndpoint(
    val ip: String = "",
    val port: Int = 9000
)

data class RobotStatus(
    val online: Boolean = false,
    val mode: String = "IDLE",
    val batteryPercent: Int = 0,
    val faultCode: String? = null,
    val joints: List<Float> = List(6) { 0f }
)

data class CommandLog(
    val timestamp: Long,
    val command: String,
    val response: String,
    val success: Boolean
)
