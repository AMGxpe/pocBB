package com.axpe.pocbb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import com.axpe.pocbb.ui.PocApp
import com.axpe.pocbb.ui.rememberPocAppState
import com.axpe.pocbb.ui.theme.PocBBTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appState = rememberPocAppState()

            CompositionLocalProvider {
                PocBBTheme {
                    PocApp(appState)
                }
            }
        }
    }
}