package org.mejdi14.project

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.mejdi14.search.LiquidSearch
import org.mejdi14.search.data.LiquidSearchConfig
import org.mejdi14.search.data.controller.rememberLiquidSearchController
import org.mejdi14.search.isMobileDevice

@Composable
@Preview
fun App() {
    MaterialTheme {
        var isChecked = remember { mutableStateOf(false) }
        val searchBarController = rememberLiquidSearchController()

        Box(Modifier.fillMaxSize().background(color = Color(0xFFe1dbff))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                searchBarController.clearFocus()
            }) {

            LiquidSearch(
                modifier = Modifier.align(Alignment.Center),
                isChecked = isChecked,
                liquidSearchConfig = LiquidSearchConfig(width = if (isMobileDevice()) 300.dp else 600.dp,
                    height = if (isMobileDevice()) 80.dp else 100.dp,)
            )

        }


    }
}
