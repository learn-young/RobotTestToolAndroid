package com.example.androidtesttool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.androidtesttool.ui.navigation.AppNavHost
import com.example.androidtesttool.ui.theme.AndroidTestToolTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidTestToolTheme {
                AppNavHost()
            }
        }
    }
}
