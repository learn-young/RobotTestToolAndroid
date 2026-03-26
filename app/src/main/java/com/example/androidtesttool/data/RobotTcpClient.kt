package com.example.androidtesttool.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.Socket

class RobotTcpClient {
    private var socket: Socket? = null
    private var writer: BufferedWriter? = null
    private var reader: BufferedReader? = null

    suspend fun connect(ip: String, port: Int): Result<Unit> = withContext(Dispatchers.IO) {
        runCatching {
            close()
            socket = Socket(ip, port)
            writer = BufferedWriter(OutputStreamWriter(socket!!.getOutputStream()))
            reader = BufferedReader(InputStreamReader(socket!!.getInputStream()))
        }
    }

    suspend fun send(command: String): Result<Unit> = withContext(Dispatchers.IO) {
        runCatching {
            checkNotNull(writer) { "Not connected" }
            writer?.apply {
                write(command)
                write("\n")
                flush()
            }
        }
    }

    suspend fun readLine(): Result<String?> = withContext(Dispatchers.IO) {
        runCatching {
            checkNotNull(reader) { "Not connected" }
            reader?.readLine()
        }
    }

    fun close() {
        runCatching { reader?.close() }
        runCatching { writer?.close() }
        runCatching { socket?.close() }
        reader = null
        writer = null
        socket = null
    }
}
