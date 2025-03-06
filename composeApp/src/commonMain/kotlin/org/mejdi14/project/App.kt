package org.mejdi14.project

import LiquidSearch
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
        MaterialTheme {
            var isCheckedemo by remember { mutableStateOf(false) }
            var textValue by remember { mutableStateOf("") }
            var layoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }
            var cursorOffset by remember { mutableStateOf(0) }
            val density = LocalDensity.current

            // Assuming each character has the same width for simplicity
            // In a real app, you might want to calculate actual text width
            val charWidth = with(density) { 14.dp.toPx() }
            Column {
                Box(Modifier.height(60.dp).fillMaxWidth()
                    .background(color = Color(0xFF6147ff))
                    .clickable {

                    }) {
                    BasicTextField(
                        value = textValue,

                        onValueChange = { newText ->
                            textValue = newText
                            // Optionally, update cursorOffset here or through other interactions
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .align(Alignment.CenterStart)
                            .onFocusChanged { focusState ->
                                if (focusState.isFocused) {
                                    isCheckedemo = true
                                } else {
                                    isCheckedemo = false
                                }
                            },
                        onTextLayout = { result ->
                            layoutResult = result
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
                    layoutResult?.let { result ->
                        // Ensure the offset is within bounds
                        val offset = cursorOffset.coerceIn(0, textValue.length)
                        // Retrieve the cursor rectangle for the current offset
                        val cursorRect = result.getCursorRect(offset)
                    LiquidSearch(
                        modifier = Modifier.size(50.dp).align(Alignment.CenterStart)
                            .graphicsLayer {
                                val cursorPosition = textValue.length * charWidth
                                translationX = cursorPosition
                            },
                        isChecked = isCheckedemo,
                        onColor = Color(0xFF6147ff),
                        offColor = Color(0xFF6147ff),
                        switchElevation = 2.dp,
                        onCheckedChange = { /* update your state */ }
                    )
                }
                }
                TextField(
                    value = textValue,
                    onValueChange = { textValue = it },
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = Color.Black
                    ),
                    modifier = Modifier.onFocusChanged {

                    }
                )
            }
        }
    }
