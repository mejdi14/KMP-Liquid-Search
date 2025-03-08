package org.mejdi14.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.Clock
import org.mejdi14.search.data.LiquidSearchConfig

@Composable
internal fun BoxScope.LiquidSearchTextField(
    textFieldValue: MutableState<TextFieldValue>,
    canvasLineSize: MutableState<Float>,
    lastInputTime: MutableState<Long>,
    liquidSearchConfig: LiquidSearchConfig,
    isChecked: MutableState<Boolean>,
    cursorOffset: MutableState<Int>,
    cancelIconIsVisible: MutableState<Boolean>,
){

    BasicTextField(
        value = textFieldValue.value,
        cursorBrush = SolidColor(Color.Transparent),
        textStyle = TextStyle(fontSize = (canvasLineSize.value * (when(isDesktop){
            PlatformName.DESKTOP  -> 4f
            PlatformName.WEB  -> {
                if (isMobileDevice()) 2f else 4f
            }
            PlatformName.MOBILE -> 2f
        })).sp, color = Color.White),
        onValueChange = { newText ->
            textFieldValue.value = newText
            cancelIconIsVisible.value = newText.text.isNotEmpty()
            lastInputTime.value = Clock.System.now().toEpochMilliseconds()
        },
        modifier = Modifier
            .fillMaxSize()
            .align(Alignment.CenterStart)
            .padding(end = (liquidSearchConfig.height / liquidSearchConfig.cancelIconSizeRatio) + (canvasLineSize.value.dp * 2.5f))
            .onFocusChanged { focusState ->
                isChecked.value = focusState.isFocused
            }
            .graphicsLayer {
                translationX = liquidSearchConfig.startSpacing
            },
        onTextLayout = { result ->
            val currentIndex = textFieldValue.value.selection.start
            var offset = 0f
            for (i in 0 until currentIndex) {
                offset += result.getBoundingBox(i).width
            }
            cursorOffset.value = offset.toInt()
        },
        singleLine = true,
        maxLines = 1,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.CenterStart
            ) {
                innerTextField()
            }
        }
    )
}
