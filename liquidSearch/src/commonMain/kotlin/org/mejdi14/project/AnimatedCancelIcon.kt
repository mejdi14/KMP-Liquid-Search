package org.mejdi14.project

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.graphicsLayer
import kotlin.math.min

@Composable
fun AnimatedCancelIcon(modifier: Modifier, canvasLineSize: MutableState<Float>) {
    Canvas(
        modifier = modifier
            .graphicsLayer {
            }
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {  }
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