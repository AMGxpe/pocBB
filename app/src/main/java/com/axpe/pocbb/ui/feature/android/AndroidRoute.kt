package com.axpe.pocbb.ui.feature.android

import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.min
import kotlin.math.roundToInt

@Composable
fun AndroidRoute() {
    AndroidScreen(Modifier.fillMaxSize())
}

enum class DragAnchors(val fraction: Float) {
    Start(0F),
    Loading(0.85F),
    End(0.95F)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun AndroidScreen(modifier: Modifier = Modifier) {
    val density = LocalDensity.current
    val state = remember {
        AnchoredDraggableState(
            initialValue = DragAnchors.Start,
            positionalThreshold = { distance: Float -> distance * 0.1F },
            velocityThreshold = { with(density) { 5000.dp.toPx() } },
            snapAnimationSpec = tween(),
            decayAnimationSpec = splineBasedDecay(density),
            confirmValueChange = { anchor ->
                anchor != DragAnchors.Start && anchor != DragAnchors.Loading
            }
        )
    }
    val contentSize = 50.dp
    val contentSizePx = with(density) { contentSize.toPx() }
    LaunchedEffect(state.currentValue) {
        Log.e("AMG", "Cambia? => ${state.currentValue}")
        if (state.currentValue == DragAnchors.End) {
            Log.d("AMG", "Petición al server")
        }
    }
    Box(modifier = modifier.padding(32.dp)) {
        Column {
            Text("Android Screen")
        }
        Box(
            Modifier
                .onSizeChanged { layoutSize ->
                    val dragEndPoint = layoutSize.width - contentSizePx
                    state.updateAnchors(
                        DraggableAnchors {
                            DragAnchors.entries
                                .forEach { anchor ->
                                    anchor at dragEndPoint * anchor.fraction
                                }
                        }
                    )
                }
                .background(Color.Gray)
                .align(Alignment.Center)
                .padding(16.dp)
                .width(240.dp)
                .height(50.dp)
        ) {
            Box(
                Modifier
                    .offset {
                        IntOffset(
                            x = min(
                                state
                                    .requireOffset()
                                    .roundToInt(), with(density){ 240.dp.roundToPx()- contentSizePx.roundToInt()}
                            ), y = 0
                        )
                    }
                    .anchoredDraggable(
                        state,
                        Orientation.Horizontal,
                        enabled = state.currentValue != DragAnchors.End
                    )
                    .size(contentSize)
                    .background(if (state.currentValue != DragAnchors.End) Color.Black else Color.Green)
            )
        }
    }
}
