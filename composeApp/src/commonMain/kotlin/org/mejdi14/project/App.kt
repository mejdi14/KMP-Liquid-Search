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
import org.jetbrains.compose.ui.tooling.preview.Preview

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

        //val charWidth = with(density) { 14.dp.toPx() }
        Column {
            Box(Modifier.height(60.dp).fillMaxWidth()
                .background(color = Color(0xFF6147ff))
                .clickable {

                }) {
                BasicTextField(
                    value = textFieldValue,
                    cursorBrush = SolidColor(Color.Transparent),
                    onValueChange = { newText ->
                        textFieldValue = newText
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
                    modifier = Modifier.size(50.dp).align(Alignment.CenterStart)
                        .graphicsLayer {
                            translationX = cursorOffset.toFloat() + 2f
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
