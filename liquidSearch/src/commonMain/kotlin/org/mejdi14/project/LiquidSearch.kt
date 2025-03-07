import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock
import org.mejdi14.project.AnimatedSearchIcon

@Composable
fun LiquidSearch(
    modifier: Modifier = Modifier.height(60.dp).width(300.dp),
    isChecked: MutableState<Boolean>,
    onColor: Color = Color(0xFF6147ff),
    offColor: Color = Color(0xFF6147ff),
    iconElevation: Dp = 4.dp,
    onCheckedChange: (Boolean) -> Unit
) {

    var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }
    var layoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }
    var cursorOffset by remember { mutableStateOf(0) }
    var lastInputTime by remember { mutableStateOf(Clock.System.now().toEpochMilliseconds()) }
    var currentTime by remember { mutableStateOf(Clock.System.now().toEpochMilliseconds()) }

    LaunchedEffect(Unit) {
        while (true) {
            currentTime = Clock.System.now().toEpochMilliseconds()
            delay(50)
        }
    }

    val infiniteTransition = rememberInfiniteTransition()
    val blinkingAlpha by infiniteTransition.animateFloat(
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
            repeatMode = RepeatMode.Restart
        )
    )

    val isTyping = (currentTime - lastInputTime) < 500
    val cursorAlpha = if (isTyping) 1f else blinkingAlpha

    Box(
        Modifier
            .height(160.dp)
            .fillMaxWidth()
            .background(color = Color(0xFF6147ff))
    ) {
        BasicTextField(
            value = textFieldValue,
            cursorBrush = SolidColor(Color.Transparent),
            onValueChange = { newText ->
                textFieldValue = newText
                lastInputTime = Clock.System.now().toEpochMilliseconds()
            },
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterStart)
                .padding(start = 25.dp)
                .onFocusChanged { focusState ->
                    isChecked.value = focusState.isFocused
                },
            onTextLayout = { result ->
                layoutResult = result
                val currentIndex = textFieldValue.selection.start
                var offset = 0f
                for (i in 0 until currentIndex) {
                    offset += result.getBoundingBox(i).width
                }
                cursorOffset = offset.toInt()
            },
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    innerTextField()
                }
            }
        )

        AnimatedSearchIcon(
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.CenterStart)
                .graphicsLayer {
                    translationX = cursorOffset.toFloat() + 2f
                    alpha = if (isChecked.value) cursorAlpha else 1f
                },
            isChecked = isChecked.value,
            onColor = onColor,
            offColor = offColor,
            switchElevation = iconElevation,
            onCheckedChange = { onCheckedChange(it) }
        )
    }
}
