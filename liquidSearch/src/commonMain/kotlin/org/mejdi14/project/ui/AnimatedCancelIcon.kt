package org.mejdi14.project.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import kotlin.math.min

@Composable
fun AnimatedCancelIcon(modifier: Modifier, canvasLineSize: MutableState<Float>) {
    Canvas(
        modifier = modifier
            .fillMaxSize()
    ) {
        val sizeMin = min(size.width, size.height)

        drawLine(
            color = Color.White,
            start = Offset(0f, 0f),
            end = Offset(sizeMin, sizeMin),
            strokeWidth = canvasLineSize.value,
            cap = StrokeCap.Round
        )

        // Draw the second line (top-right to bottom-left)
        drawLine(
            color = Color.White,
            start = Offset(sizeMin, 0f),
            end = Offset(0f, sizeMin),
            strokeWidth = canvasLineSize.value,
            cap = StrokeCap.Round
        )
    }

}