package com.axpe.pocbb.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.axpe.pocbb.ui.PocAppState
import com.axpe.pocbb.ui.feature.android.navigation.androidScreen
import com.axpe.pocbb.ui.feature.cart.navigation.cartScreen
import com.axpe.pocbb.ui.feature.placeholder.navigation.placeholderScreen
import com.axpe.pocbb.ui.feature.profile.navigation.profileScreen
import com.axpe.pocbb.ui.feature.users.navigation.UsersRoute
import com.axpe.pocbb.ui.feature.users.navigation.usersScreen

@Composable
fun PocNavHost(appState: PocAppState, modifier: Modifier = Modifier) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = UsersRoute,
        modifier = modifier
    ) {
        usersScreen()
        profileScreen()
        cartScreen()
        placeholderScreen()
        androidScreen()
    }
}