package com.axpe.pocbb.ui.feature.placeholder

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

@Composable
fun PlaceholderRoute() {
    PlaceholderScreen(Modifier.fillMaxSize())
}


@Composable
fun PlaceholderScreen(modifier: Modifier = Modifier) {
    AwesomeTextField(hint = "Any hint", modifier = Modifier.padding(16.dp))
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AwesomeTextField(hint: String, modifier: Modifier = Modifier) {
    var text by remember {
        mutableStateOf("")
    }
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val showHitAbove by remember {
        derivedStateOf { isFocused || text.isNotEmpty() }
    }
    Column(modifier = modifier) {
        BasicTextField(
            value = text,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
            textStyle = TextStyle.Default.copy(color = MaterialTheme.colorScheme.onSurface),
            onValueChange = { text = it },
            decorationBox = { innerTextField ->
                SharedTransitionLayout {
                    AnimatedContent(
                        targetState = showHitAbove,
                        transitionSpec = {
                            EnterTransition.None togetherWith ExitTransition.None
                        },
                        label = "hintAnimation"
                    ) { showHitAbove ->
                        Column {
                            Box(modifier = Modifier.padding(2.dp)) {
                                if (showHitAbove) {
                                    TextAsIndividualLetters(
                                        this@AnimatedContent,
                                        hint,
                                        style = MaterialTheme.typography.labelLarge.copy(color = MaterialTheme.colorScheme.primary)
                                    )
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .sharedElement(
                                        rememberSharedContentState(key = "input"),
                                        animatedVisibilityScope = this@AnimatedContent
                                    )
                                    .defaultMinSize(minWidth = 300.dp)
                                    .background(
                                        color = MaterialTheme.colorScheme.surface,
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                    .border(
                                        width = Dp.Hairline,
                                        shape = RoundedCornerShape(10.dp),
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = .3F)
                                    )
                                    .padding(horizontal = 16.dp, vertical = 16.dp),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                if (!showHitAbove) {
                                    TextAsIndividualLetters(
                                        this@AnimatedContent,
                                        hint,
                                        style = TextStyle.Default.copy(
                                            color = MaterialTheme.colorScheme.tertiary,
                                            letterSpacing = TextUnit(
                                                value = 16F,
                                                type = TextUnitType.Sp
                                            )
                                        )
                                    )
                                }
                                innerTextField()
                            }
                        }
                    }
                }

            })
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.TextAsIndividualLetters(
    animatedContentScope: AnimatedContentScope,
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle()
) {
    Row(modifier = modifier) {
        text.forEachIndexed { index, letter ->
            Text(
                "$letter",
                modifier = Modifier.sharedBounds(
                    sharedContentState = rememberSharedContentState(key = "hint_$index"),
                    animatedVisibilityScope = animatedContentScope,
                    boundsTransform = { _, _ ->
                        spring(
                            dampingRatio = Spring.DampingRatioLowBouncy,
                            stiffness = Spring.StiffnessVeryLow * (text.length - index)
                        )
                    }
                ),
                style = style
            )
        }
    }
}