import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateFloat
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.min

// Constants matching the original implementation
const val SWITCHER_ANIMATION_DURATION = 800L
const val COLOR_ANIMATION_DURATION = 300L
const val BOUNCE_ANIM_AMPLITUDE_IN = 0.2
const val BOUNCE_ANIM_AMPLITUDE_OUT = 0.15
const val BOUNCE_ANIM_FREQUENCY_IN = 14.5
const val BOUNCE_ANIM_FREQUENCY_OUT = 12.0

// A simple linear interpolation function
fun lerp(a: Float, b: Float, t: Float): Float = a + (b - a) * t

@Composable
fun SwitcherCompose(
    modifier: Modifier = Modifier.size(50.dp),
    isChecked: Boolean,
    onColor: Color,
    offColor: Color,
    switchElevation: Dp = 4.dp,
    onCheckedChange: (Boolean) -> Unit
) {
    val density = LocalDensity.current
    // Convert elevation to pixels (used as an offset in our drawing)
    val elevationPx = with(density) { switchElevation.toPx() }

    // Create a transition for the two states
    val transition = updateTransition(targetState = isChecked, label = "SwitcherTransition")

    // Animate the icon progress (0 when checked, 1 when unchecked)
    val iconProgress by transition.animateFloat(
        transitionSpec = {
            // Choose different bounce parameters depending on state.
            val amplitude = if (targetState) BOUNCE_ANIM_AMPLITUDE_OUT else BOUNCE_ANIM_AMPLITUDE_IN
            val frequency = if (targetState) BOUNCE_ANIM_FREQUENCY_OUT else BOUNCE_ANIM_FREQUENCY_IN
            tween(
                durationMillis = SWITCHER_ANIMATION_DURATION.toInt(),
                easing = Easing { fraction ->
                    // Bounce formula similar to the original implementation
                    (-1 * exp(-fraction / amplitude) * cos(frequency * fraction) + 1).toFloat()
                }
            )
        },
        label = "IconProgressAnimation"
    ) { state ->
        if (state) 0f else 1f
    }

    // Animate the color transition
    val currentColor by transition.animateColor(
        transitionSpec = { tween(durationMillis = COLOR_ANIMATION_DURATION.toInt()) },
        label = "ColorAnimation"
    ) { state ->
        if (state) onColor else offColor
    }

    Canvas(
        modifier = modifier
            .clickable { onCheckedChange(!isChecked) }
            .fillMaxSize()
    ) {
        // Use the smaller dimension (width or height) for our calculations
        val sizeMin = min(size.width, size.height)
        // Calculate the switcher radius
        val switcherRadius = (sizeMin / 2f) - elevationPx

        // Draw the background circle


        // Calculate icon geometry based on the switcher size
        val iconRadius = switcherRadius * 0.5f
        val iconClipRadius = iconRadius / 2.25f
        val iconCollapsedWidth = (iconRadius - iconClipRadius) * 1.1f
        val iconHeight = iconRadius * 2f

        // Compute the icon offset based on the animated iconProgress
        val iconOffset = lerp(0f, iconRadius - iconCollapsedWidth / 2f, iconProgress)

        // Define base icon rectangle positioning
        val baseIconLeft = (switcherRadius - iconCollapsedWidth / 2f - iconOffset) + elevationPx
        val baseIconRight = (switcherRadius + iconCollapsedWidth / 2f + iconOffset) + elevationPx
        val baseIconTop = ((switcherRadius * 2f - iconHeight) / 2f) + elevationPx / 2f
        val baseIconBottom = (switcherRadius * 2f - (switcherRadius * 2f - iconHeight) / 2f) + elevationPx / 2f

        var iconRect = Rect(baseIconLeft, baseIconTop, baseIconRight, baseIconBottom)
        val fixedIconProgress = 0f // represents checked state
        val fixedIconOffset = lerp(0f, iconRadius - iconCollapsedWidth / 2f, fixedIconProgress)
        val fixedIconLeft = (switcherRadius - iconCollapsedWidth / 2f - fixedIconOffset) + elevationPx
        val fixedIconRight = (switcherRadius + iconCollapsedWidth / 2f + fixedIconOffset) + elevationPx
        val fixedIconWidth = fixedIconRight - fixedIconLeft
        val lineSize = fixedIconWidth

        // Adjust icon rectangle when unchecked, but limit the expansion
        if (!isChecked) {
            iconRect = Rect(
                iconRect.left ,
                iconRect.top,
                iconRect.right ,
                iconRect.bottom
            )
        }


        // Draw the icon as a rounded rectangle
        drawRoundRect(
            color = Color.White,
            topLeft = Offset(iconRect.left , iconRect.top),
            size = Size(iconRect.width, iconRect.height),
            cornerRadius = CornerRadius(switcherRadius , switcherRadius )
        )

        // Add search line
        drawLine(
            color = Color.White,
            start = Offset( iconRect.left + (lineSize / 2) + ((iconRect.size.width / 2 ) * iconProgress), iconRect.bottom - (elevationPx) - (lineSize * iconProgress)),
            end = Offset(iconRect.left + (lineSize / 2) + (iconRect.size.width * iconProgress), iconRect.bottom + elevationPx * 0.5f + switcherRadius * 0.7f - ((lineSize * 2) * iconProgress)),
            strokeWidth = lineSize,
            cap = StrokeCap.Round
        )

        // Calculate the clip rectangle for the icon
        val clipOffset = lerp(0f, iconClipRadius, iconProgress)
        val iconCenter = Offset(iconRect.center.x, iconRect.center.y)
        val iconClipRect = Rect(
            iconCenter.x - clipOffset,
            iconCenter.y - clipOffset,
            iconCenter.x + clipOffset,
            iconCenter.y + clipOffset
        )

        // Draw the clip if its width is greater than the collapsed width
        if (iconClipRect.width > iconCollapsedWidth) {
            drawRoundRect(
                color = currentColor,
                topLeft = Offset(iconClipRect.left, iconClipRect.top),
                size = Size(iconClipRect.width, iconClipRect.height),
                cornerRadius = CornerRadius(iconRadius, iconRadius)
            )
        }
    }
}