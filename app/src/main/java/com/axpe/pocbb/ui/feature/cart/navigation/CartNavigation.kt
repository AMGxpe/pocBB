package com.axpe.pocbb.ui.feature.cart.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.axpe.pocbb.ui.feature.cart.CartRoute
import kotlinx.serialization.Serializable


@Serializable
object CartRoute

fun NavController.navigateToCart(navOptions: NavOptions) {
    navigate(CartRoute, navOptions)
}

fun NavGraphBuilder.cartScreen() {
    composable<CartRoute> {
        CartRoute()
    }
}