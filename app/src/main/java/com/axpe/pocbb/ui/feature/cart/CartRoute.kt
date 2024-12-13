package com.axpe.pocbb.ui.feature.cart

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CartRoute() {
    CartScreen(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    )
}


@Composable
fun CartScreen(modifier: Modifier = Modifier) {

    val (username, setUsername) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }

    Column(modifier = modifier) {
        CustomField(username, setUsername, "Username")
        Spacer(Modifier.height(24.dp))
        CustomField(password, setPassword, "Password")
    }
}

@Composable
fun CustomField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    modifier: Modifier = Modifier
) {
    var borderTopEndFocused by remember { mutableStateOf(false) }
    val borderTopEnd: Float by animateFloatAsState(if (borderTopEndFocused) 24F else 2500F)
    val borderWidth: Dp by animateDpAsState(if (borderTopEndFocused) 1.dp else 0.dp)
    val borderColor: Color by animateColorAsState(if (borderTopEndFocused) Color.Red else Color.Transparent)
    TextField(
        value,
        onValueChange,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        placeholder = { Text(placeholderText) },
        shape = RoundedCornerShape(
            topStart = 8F,
            topEnd = borderTopEnd,
            bottomEnd = 8F,
            bottomStart = 8F
        ),
        modifier = modifier
            .onFocusChanged {
                borderTopEndFocused = it.isFocused
            }
            .border(
                width = borderWidth,
                color = borderColor,
                shape = RoundedCornerShape(
                    topStart = 8F,
                    topEnd = borderTopEnd,
                    bottomEnd = 8F,
                    bottomStart = 8F
                )
            )
    )

}