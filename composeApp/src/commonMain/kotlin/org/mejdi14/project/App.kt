package org.mejdi14.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.mejdi14.project.data.LiquidSearchConfig

@Composable
@Preview
fun App() {
    MaterialTheme {
        var isChecked = remember { mutableStateOf(false) }
        Box(Modifier.fillMaxSize().background(color = Color(0xFFe1dbff))){

        LiquidSearch(
            modifier = Modifier.align(Alignment.Center),
            isChecked = isChecked,
            liquidSearchConfig = LiquidSearchConfig(width = 600.dp))

        }


    }
}
