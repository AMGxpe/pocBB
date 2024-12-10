package com.axpe.pocbb.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.axpe.pocbb.ui.feature.android.navigation.AndroidRoute
import com.axpe.pocbb.ui.feature.cart.navigation.CartRoute
import com.axpe.pocbb.ui.feature.placeholder.navigation.PlaceholderRoute
import com.axpe.pocbb.ui.feature.profile.navigation.ProfileRoute
import com.axpe.pocbb.ui.feature.users.navigation.UsersRoute
import kotlin.reflect.KClass

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
//    @StringRes val iconTextId: Int,
//    @StringRes val titleTextId: Int,
    val route: KClass<*>,
    val baseRoute: KClass<*> = route,
) {
    HOME(
        selectedIcon = Icons.Default.Home,
        unselectedIcon = Icons.Outlined.Home,
        route = UsersRoute::class
    ),
    PROFILE(
        selectedIcon = Icons.Default.Person,
        unselectedIcon = Icons.Outlined.Person,
        route = ProfileRoute::class
    ),
    CART(
        selectedIcon = Icons.Default.ShoppingCart,
        unselectedIcon = Icons.Outlined.ShoppingCart,
        route = CartRoute::class
    ),
    PLACEHOLDER(
        selectedIcon = Icons.Default.Place,
        unselectedIcon = Icons.Outlined.Place,
        route = PlaceholderRoute::class
    ),
    ANDROID(
        selectedIcon = Icons.Default.Star,
        unselectedIcon = Icons.Outlined.Star,
        route = AndroidRoute::class
    ),


}
