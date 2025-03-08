package org.mejdi14.search.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import org.mejdi14.search.data.LiquidSearchConfig
import kotlin.math.min

@Composable
fun AnimatedCancelIcon(
    modifier: Modifier,
    canvasLineSize: MutableState<Float>,
    liquidSearchConfig: LiquidSearchConfig
) {
    Canvas(
        modifier = modifier
            .fillMaxSize()
    ) {
        val sizeMin = min(size.width, size.height)

        drawLine(
            color = liquidSearchConfig.cancelIconColor,
            start = Offset(0f, 0f),
            end = Offset(sizeMin, sizeMin),
            strokeWidth = canvasLineSize.value,
            cap = StrokeCap.Round
        )

        // Draw the second line (top-right to bottom-left)
        drawLine(
            color = liquidSearchConfig.cancelIconColor,
            start = Offset(sizeMin, 0f),
            end = Offset(0f, sizeMin),
            strokeWidth = canvasLineSize.value,
            cap = StrokeCap.Round
        )
    }

}