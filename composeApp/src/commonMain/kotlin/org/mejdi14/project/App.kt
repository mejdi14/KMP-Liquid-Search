package org.mejdi14.project

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        var isChecked = remember { mutableStateOf(false) }
        Box(Modifier.fillMaxSize()){

        LiquidSearch(isChecked = isChecked)

        }


    }
}
