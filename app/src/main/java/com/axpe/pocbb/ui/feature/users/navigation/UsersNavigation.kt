package com.axpe.pocbb.ui.feature.users.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.axpe.pocbb.ui.feature.users.UsersRoute
import kotlinx.serialization.Serializable

@Serializable
object UsersRoute

fun NavController.navigateToUsers(navOptions: NavOptions) = navigate(route = UsersRoute, navOptions)

fun NavGraphBuilder.usersScreen() {
    composable<UsersRoute> {
        UsersRoute()
    }
}

