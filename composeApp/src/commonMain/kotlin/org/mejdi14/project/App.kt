package org.mejdi14.project

import LiquidSearch
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
        MaterialTheme {
            var isCheckedemo by remember { mutableStateOf(false) }
            Box(Modifier.fillMaxSize()
                .background(color = Color(0xFF6147ff))
                .clickable {
                    isCheckedemo = !isCheckedemo
                }) {
                LiquidSearch(
                    isChecked = isCheckedemo,
                    onColor = Color(0xFF6147ff),
                    offColor = Color(0xFF6147ff),
                    switchElevation = 2.dp,
                    onCheckedChange = { /* update your state */ }
                )
            }
        }
    }
