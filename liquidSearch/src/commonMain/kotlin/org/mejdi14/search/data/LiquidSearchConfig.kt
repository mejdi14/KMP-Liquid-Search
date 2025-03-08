package org.mejdi14.search.data

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.mejdi14.search.data.listener.LiquidSearchActionListener
import org.mejdi14.search.data.listener.defaultLiquidSearchActionListener

data class LiquidSearchConfig(
    val height: Dp = 100.dp,
    val width: Dp? = null,
    val shape: Shape = RoundedCornerShape(25.dp),
    val backgroundColor: Color = Color(0xFF6147ff),
    val padding: PaddingValues = PaddingValues(20.dp),
    val startSpacing: Float = 30f,
    val textFieldConfig: TextFieldConfig = TextFieldConfig(),
    val liquidSearchIconPosition: LiquidSearchIconPosition = LiquidSearchIconPosition.LEFT,
    val cancelIconSizeRatio: Int = 5,
    val searchIconColor: Color = Color.White,
    val cancelIconColor: Color = Color.White,
    val iconActiveColor: Color = Color.Transparent,
    val iconInactiveColor: Color = backgroundColor,
    val liquidSearchActionListener: LiquidSearchActionListener = defaultLiquidSearchActionListener,

    )
