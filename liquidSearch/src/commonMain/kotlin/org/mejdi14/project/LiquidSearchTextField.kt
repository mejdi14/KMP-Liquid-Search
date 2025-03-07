package org.mejdi14.project

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import kotlinx.datetime.Clock
import org.mejdi14.project.data.LiquidSearchConfig

@Composable
internal fun BoxScope.LiquidSearchTextField(
    textFieldValue: MutableState<TextFieldValue>,
    canvasLineSize: MutableState<Float>,
    lastInputTime: MutableState<Long>,
    liquidSearchConfig: LiquidSearchConfig,
    isChecked: MutableState<Boolean>,
    cursorOffset: MutableState<Int>
){

    BasicTextField(
        value = textFieldValue.value,
        cursorBrush = SolidColor(Color.Transparent),
        textStyle = TextStyle(fontSize = (canvasLineSize.value * 2).sp, color = Color.White),
        onValueChange = { newText ->
            textFieldValue.value = newText
            lastInputTime.value = Clock.System.now().toEpochMilliseconds()
        },
        modifier = Modifier
            .fillMaxSize()
            .align(Alignment.CenterStart)
            .padding(start = liquidSearchConfig.height / 2)
            .onFocusChanged { focusState ->
                isChecked.value = focusState.isFocused
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
