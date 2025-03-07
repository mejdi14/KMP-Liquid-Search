package org.mejdi14.project

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class LiquidSearchConfig(
    val height: Dp = 170.dp,
    val width: Dp? = null,
    val shape: Shape = RoundedCornerShape(10.dp),
    val backgroundColor: Color = Color(0xFF6147ff),
)
