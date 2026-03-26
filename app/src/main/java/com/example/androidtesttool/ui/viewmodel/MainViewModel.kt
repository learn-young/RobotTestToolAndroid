package com.example.androidtesttool.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtesttool.data.RobotRepository
import com.example.androidtesttool.domain.ConnectionState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: RobotRepository = RobotRepository()
) : ViewModel() {
    val connectionState = repository.connectionState.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        ConnectionState.DISCONNECTED
    )

    val robotStatus = repository.status.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        repository.status.value
    )

    val logs: StateFlow<List<com.example.androidtesttool.domain.CommandLog>> = repository.logs.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    fun connect(ip: String, port: Int) {
        viewModelScope.launch {
            repository.connect(ip, port)
        }
    }

    fun disconnect() {
        repository.disconnect()
    }

    fun sendCommand(command: String) {
        viewModelScope.launch {
            repository.sendCommand(command)
            repository.mockUpdateStatus()
        }
    }
}
