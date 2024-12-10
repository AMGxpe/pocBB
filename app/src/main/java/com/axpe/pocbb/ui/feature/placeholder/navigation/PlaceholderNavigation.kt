package com.axpe.pocbb.ui.feature.placeholder.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.axpe.pocbb.ui.feature.placeholder.PlaceholderRoute
import kotlinx.serialization.Serializable

@Serializable
object PlaceholderRoute

fun NavController.navigateToPlaceholder(navOptions: NavOptions) =
    navigate(PlaceholderRoute, navOptions)


fun NavGraphBuilder.placeholderScreen() {
    composable<PlaceholderRoute> {
        PlaceholderRoute()
    }
}