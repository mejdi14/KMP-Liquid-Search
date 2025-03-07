package org.mejdi14.project.data

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class LiquidSearchConfig(
    val height: Dp = 80.dp,
    val width: Dp? = null,
    val shape: Shape = RoundedCornerShape(10.dp),
    val backgroundColor: Color = Color(0xFF6147ff),
    val padding: PaddingValues = PaddingValues(20.dp)
)
