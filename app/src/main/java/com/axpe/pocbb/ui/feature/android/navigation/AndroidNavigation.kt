package com.axpe.pocbb.ui.feature.android.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.axpe.pocbb.ui.feature.android.AndroidRoute
import kotlinx.serialization.Serializable


@Serializable
object AndroidRoute

fun NavController.navigateToAndroid(navOptions: NavOptions) = navigate(AndroidRoute, navOptions)


fun NavGraphBuilder.androidScreen() {
    composable<AndroidRoute> {
        AndroidRoute()
    }
}