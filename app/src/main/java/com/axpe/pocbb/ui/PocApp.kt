package com.axpe.pocbb.ui

import PocAwesomeBottomBarMenu
import PocBottomBarMenu
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.axpe.pocbb.navigation.PocNavHost

@Composable
fun PocApp(
    appState: PocAppState,
    modifier: Modifier = Modifier
) {
    val currentDestination = appState.currentTopLevelDestination
    Scaffold(modifier = modifier, bottomBar = {
        PocAwesomeBottomBarMenu(appState.topLevelDestination, currentDestination) {
            appState.navigateToTopLevelDestination(it)
        }
    }) { innerPadding ->
        PocNavHost(appState, Modifier.padding(innerPadding))
    }

}