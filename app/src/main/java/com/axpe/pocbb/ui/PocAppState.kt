package com.axpe.pocbb.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.axpe.pocbb.navigation.TopLevelDestination
import com.axpe.pocbb.ui.feature.android.navigation.navigateToAndroid
import com.axpe.pocbb.ui.feature.cart.navigation.navigateToCart
import com.axpe.pocbb.ui.feature.placeholder.navigation.navigateToPlaceholder
import com.axpe.pocbb.ui.feature.profile.navigation.navigateToProfile
import com.axpe.pocbb.ui.feature.users.navigation.navigateToUsers
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberPocAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController()
): PocAppState = remember(
    navController,
    coroutineScope
) {
    PocAppState(navController, coroutineScope)
}

@Stable
class PocAppState(
    val navController: NavHostController,
    coroutineScope: CoroutineScope,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() {
            return TopLevelDestination.entries.firstOrNull { topLevelDestination ->
                currentDestination?.hasRoute(route = topLevelDestination.route) == true
            }
        }
    val topLevelDestination: List<TopLevelDestination> = TopLevelDestination.entries

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (topLevelDestination) {
            TopLevelDestination.HOME -> navController.navigateToUsers(topLevelNavOptions)
            TopLevelDestination.PROFILE -> navController.navigateToProfile(topLevelNavOptions)
            TopLevelDestination.PLACEHOLDER -> navController.navigateToPlaceholder(
                topLevelNavOptions
            )

            TopLevelDestination.CART -> navController.navigateToCart(topLevelNavOptions)
            TopLevelDestination.ANDROID -> navController.navigateToAndroid(topLevelNavOptions)
        }
    }
}