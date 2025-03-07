package org.mejdi14.project

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock
import org.mejdi14.project.data.LiquidSearchConfig

@Composable
fun LiquidSearch(
    modifier: Modifier = Modifier,
    liquidSearchConfig: LiquidSearchConfig = LiquidSearchConfig(),
    isChecked: MutableState<Boolean>,
    onColor: Color = Color(0xFF6147ff),
    offColor: Color = Color(0xFF6147ff),
    iconElevation: Dp = 4.dp,
    onCheckedChange: (Boolean) -> Unit
) {

    var textFieldValue = remember { mutableStateOf(TextFieldValue("")) }
    var cursorOffset = remember { mutableStateOf(0) }
    var lastInputTime = remember { mutableStateOf(Clock.System.now().toEpochMilliseconds()) }
    var currentTime = remember { mutableStateOf(Clock.System.now().toEpochMilliseconds()) }
    var canvasLineSize = remember { mutableStateOf(0f) }
    LaunchedEffect(Unit) {
        while (true) {
            currentTime.value = Clock.System.now().toEpochMilliseconds()
            delay(50)
        }
    }

    val blinkingAlpha by if (isChecked.value) {
        rememberInfiniteTransition().animateFloat(
            initialValue = 1f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = 900
                    1f at 0
                    0f at 100
                    0f at 400
                    1f at 500
                },
                initialStartOffset = StartOffset(300),
                repeatMode = RepeatMode.Restart
            )
        )
    } else {
        remember { mutableStateOf(1f) }
    }

    val isTyping = (currentTime.value - lastInputTime.value) < 500
    val cursorAlpha = if (isTyping) 1f else blinkingAlpha

    Box(
        modifier
            .height(liquidSearchConfig.height)
            .then(
                liquidSearchConfig.width?.let {
                    Modifier.width(it)
                } ?: Modifier.fillMaxWidth()
            )
            .background(
                color = liquidSearchConfig.backgroundColor,
                shape = liquidSearchConfig.shape
            )
            .padding(liquidSearchConfig.padding)
    ) {
        LiquidSearchTextField(
            textFieldValue,
            canvasLineSize,
            lastInputTime,
            liquidSearchConfig,
            isChecked,
            cursorOffset
        )

        Row(Modifier.fillMaxSize()) {
            Box(modifier = Modifier.fillMaxHeight().weight(1f)) {
                AnimatedSearchIcon(
                    modifier = Modifier
                        .size(liquidSearchConfig.height)
                        .align(Alignment.CenterStart)
                        .graphicsLayer {
                            translationX =
                                cursorOffset.value.toFloat() + liquidSearchConfig.padding.calculateStartPadding(
                                    LayoutDirection.Ltr
                                )
                                    .toPx() + (canvasLineSize.value / 2) - (liquidSearchConfig.height.toPx() / 2)
                            alpha = if (isChecked.value) cursorAlpha else 1f
                        },
                    isChecked = isChecked.value,
                    onColor = onColor,
                    offColor = offColor,
                    switchElevation = iconElevation,
                    canvasLineSize,
                    onCheckedChange = { onCheckedChange(it) }
                )
            }
        }
        AnimatedCancelIcon(
            Modifier.size(liquidSearchConfig.height / liquidSearchConfig.cancelIconSizeRatio)
                .align(Alignment.CenterEnd),
            canvasLineSize
        )
    }
}

