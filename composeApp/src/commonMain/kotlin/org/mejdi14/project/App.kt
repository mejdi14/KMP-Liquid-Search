package org.mejdi14.project

import SwitcherCompose
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import kmp_liquid_search.composeapp.generated.resources.Res
import kmp_liquid_search.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.reload.DevelopmentEntryPoint

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
                SwitcherCompose(
                    isChecked = isCheckedemo,
                    onColor = Color(0xFF6147ff),
                    offColor = Color(0xFF6147ff),
                    switchElevation = 2.dp,
                    onCheckedChange = { /* update your state */ }
                )
            }
        }
    }
