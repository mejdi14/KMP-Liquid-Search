package org.mejdi14.project

import LiquidSearch
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.Clock.System

@Composable
@Preview
fun App() {
    MaterialTheme {
        var isCheckedemo by remember { mutableStateOf(false) }
        var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }
        var layoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }
        var cursorOffset by remember { mutableStateOf(0) }
        val density = LocalDensity.current
        var charWidth by remember { mutableStateOf(0f) }
        var lastInputTime by remember { mutableStateOf(Clock.System.now().toEpochMilliseconds()) }
        var currentTime by remember { mutableStateOf(Clock.System.now().toEpochMilliseconds()) }

        LaunchedEffect(Unit) {
            while (true) {
                currentTime = Clock.System.now().toEpochMilliseconds()
                delay(50)
            }
        }

        // Blinking animation using infinite transition
        val infiniteTransition = rememberInfiniteTransition()
        val blinkingAlpha by infiniteTransition.animateFloat(
            initialValue = 1f,
            targetValue = 1f, // Not used since keyframes override it
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = 900
                    1f at 0
                    0f at 100
                    0f at 400
                    1f at 500
                },
                repeatMode = RepeatMode.Restart
            )
        )

        // Determine if the user is "actively typing" using a threshold (e.g., 500ms)
        val isTyping = (currentTime - lastInputTime) < 500
        // Use blinkingAlpha when not typing, or full visibility when typing
        val cursorAlpha = if (isTyping) 1f else blinkingAlpha

        //val charWidth = with(density) { 14.dp.toPx() }
        Column {
            Box(Modifier.height(160.dp).fillMaxWidth()
                .background(color = Color(0xFF6147ff))
                .clickable {

                }) {
                BasicTextField(
                    value = textFieldValue,
                    cursorBrush = SolidColor(Color.Transparent),
                    onValueChange = { newText ->
                        textFieldValue = newText
                        lastInputTime = Clock.System.now().toEpochMilliseconds()
                    },

                    modifier = Modifier
                        .fillMaxSize()

                        .align(Alignment.CenterStart)
                        .padding(start = 25.dp)
                        .onFocusChanged { focusState ->
                            if (focusState.isFocused) {
                                isCheckedemo = true
                            } else {
                                isCheckedemo = false
                            }
                        },
                    onTextLayout = { result ->
                        layoutResult = result
                        val currentIndex = textFieldValue.selection.start
                        var offset = 0f
                        for (i in 0 until currentIndex) {
                            offset += result.getBoundingBox(i).width
                        }
                        cursorOffset = offset.toInt()
                    },
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            innerTextField()
                        }
                    }
                )

                LiquidSearch(
                    modifier = Modifier.size(150.dp).align(Alignment.CenterStart)
                        .graphicsLayer {
                            translationX = cursorOffset.toFloat() + 2f
                            //alpha = if(isCheckedemo) cursorAlpha else 1f
                            alpha = 1f
                        },
                    isChecked = isCheckedemo,
                    onColor = Color(0xFF6147ff),
                    offColor = Color(0xFF6147ff),
                    switchElevation = 2.dp,
                    onCheckedChange = { /* update your state */ }
                )
            }
            TextField(
                value = textFieldValue,
                onValueChange = { textFieldValue = it },
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color.Black
                ),
                modifier = Modifier.onFocusChanged {

                }
            )
        }
    }
}
