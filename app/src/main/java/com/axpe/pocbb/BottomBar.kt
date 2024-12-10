import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.axpe.pocbb.navigation.TopLevelDestination


@Composable
fun PocBottomBarMenu(
    destinations: List<TopLevelDestination>,
    currentDestination: TopLevelDestination?,
    onNavigateTo: (TopLevelDestination) -> Unit
) {
    NavigationBar {
        destinations.forEach { destination ->
            NavigationBarItem(
                destination == currentDestination,
                onClick = { onNavigateTo(destination) },
                icon = {
                    Icon(
                        if (destination == currentDestination) destination.selectedIcon else destination.unselectedIcon,
                        null
                    )
                },
            )

        }
    }
}

@Composable
fun PocAwesomeBottomBarMenu(
    screens: List<TopLevelDestination>,
    currentScreen: TopLevelDestination?,
    onNavigateTo: (TopLevelDestination) -> Unit
) {
    val backgroundShape = remember { menuBarShape() }
    val density = LocalDensity.current
    if (screens.size < 4) {
        return PocBottomBarMenu(screens, currentScreen, onNavigateTo)
    }
    Box {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(WindowInsets.navigationBars.getBottom(density).dp/3 + 56.dp)
                .background(Color.White, backgroundShape)
                .align(Alignment.BottomCenter)
        )

        Column(modifier = Modifier.align(Alignment.TopCenter).padding(bottom = WindowInsets.navigationBars.getBottom(density).dp/2)) {
            FloatingActionButton(
                shape = RoundedCornerShape(50),
                containerColor = Color.White,
                contentColor = Color.Gray,
                onClick = {},
                modifier = Modifier.clip(RoundedCornerShape(50))
            ) {
                Row(modifier = Modifier.size(64.dp)) {
                    BottomBarItem(screens[2], currentScreen, onNavigateTo)
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
        }
        Row(
            modifier = Modifier
                .padding(bottom = WindowInsets.navigationBars.getBottom(density).dp/3)
                .height(56.dp)
                .align(Alignment.BottomCenter)
        ) {
            BottomBarItem(screens[0], currentScreen, onNavigateTo)
            BottomBarItem(screens[1], currentScreen, onNavigateTo)

            Spacer(modifier = Modifier.width(72.dp))

            BottomBarItem(screens[3], currentScreen, onNavigateTo)
            BottomBarItem(screens[4], currentScreen, onNavigateTo)
        }

    }
}

@Composable
private fun RowScope.BottomBarItem(
    screen: TopLevelDestination,
    currentScreen: TopLevelDestination?,
    onNavigateTo: (TopLevelDestination) -> Unit,
) {
    val selected = currentScreen?.route == screen.route

    Box(
        Modifier
            .selectable(
                selected = selected,
                onClick = { onNavigateTo(screen) },
                role = Role.Tab,
                interactionSource = remember { MutableInteractionSource() },
                indication = remember { ripple(radius = 32.dp) }
            )
            .fillMaxHeight()
            .weight(1f),
        contentAlignment = Alignment.Center
    ) {
        BadgedBox(
            badge = {},
            content = {
                Icon(
                    imageVector = if (selected) {
                        screen.selectedIcon
                    } else screen.unselectedIcon,
                    contentDescription = null
                )
            },
        )
    }
}

private fun menuBarShape() = GenericShape { size, _ ->
    reset()
    moveTo(0F, 0F)

    val width = 150F
    val height = 90F

    val point1 = 75F
    val point2 = 85F

    lineTo(size.width / 2 - width, 0f)

    cubicTo(
        size.width / 2 - point1, 0f, size.width / 2 - point2, height, size.width / 2, height
    )

    cubicTo(
        size.width / 2 + point2, height, size.width / 2 + point1, 0f, size.width / 2 + width, 0f
    )

    lineTo(size.width / 2 + width, 0f)

    lineTo(size.width, 0f)
    lineTo(size.width, size.height)
    lineTo(0f, size.height)

    close()
}
