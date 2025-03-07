package org.mejdi14.project

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateFloat
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.mejdi14.project.helpers.ANIMATION_SPEED_EXIT
import org.mejdi14.project.helpers.BOUNCE_ANIM_AMPLITUDE_IN
import org.mejdi14.project.helpers.BOUNCE_ANIM_AMPLITUDE_OUT
import org.mejdi14.project.helpers.BOUNCE_ANIM_FREQUENCY_IN
import org.mejdi14.project.helpers.COLOR_ANIMATION_DURATION
import org.mejdi14.project.helpers.ELEVATION_LINE_FACTOR
import org.mejdi14.project.helpers.ICON_CLIP_RADIUS_FACTOR
import org.mejdi14.project.helpers.ICON_COLLAPSED_WIDTH_SCALE
import org.mejdi14.project.helpers.ICON_RADIUS_FACTOR
import org.mejdi14.project.helpers.LINE_END_X_FACTOR
import org.mejdi14.project.helpers.LINE_SQUEEZE_BOUNCE_FACTOR
import org.mejdi14.project.helpers.LINE_START_X_FACTOR
import org.mejdi14.project.helpers.RECT_CORNER_FACTOR
import org.mejdi14.project.helpers.SWITCHER_ANIMATION_DURATION
import org.mejdi14.project.helpers.SWITCH_RADIUS_LINE_FACTOR
import org.mejdi14.project.helpers.TRANSLATION_Y_CHECKED_FACTOR
import org.mejdi14.project.helpers.lerp
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.min

// Icon dimensions and proportions


@Composable
internal fun AnimatedSearchIcon(
    modifier: Modifier,
    isChecked: Boolean,
    onColor: Color,
    offColor: Color,
    switchElevation: Dp = 4.dp,
    canvasLineSize: MutableState<Float>,
    onCheckedChange: (Boolean) -> Unit
) {
    val density = LocalDensity.current
    val elevationPx = with(density) { switchElevation.toPx() }

    val transition = updateTransition(targetState = isChecked, label = "Search Icon Animation")

    val iconProgress by transition.animateFloat(
        transitionSpec = {
            val amplitude = if (targetState) BOUNCE_ANIM_AMPLITUDE_OUT else BOUNCE_ANIM_AMPLITUDE_IN
            val frequency = if (targetState) ANIMATION_SPEED_EXIT else BOUNCE_ANIM_FREQUENCY_IN
            tween(
                durationMillis = SWITCHER_ANIMATION_DURATION.toInt(),
                easing = Easing { fraction ->
                    (-1 * exp(-fraction / amplitude) * cos(frequency * fraction) + 1).toFloat()
                }
            )
        },
        label = "IconProgressAnimation"
    ) { state ->
        if (state) 0f else 1f
    }

    val currentColor by transition.animateColor(
        transitionSpec = { tween(durationMillis = COLOR_ANIMATION_DURATION.toInt()) },
        label = "ColorAnimation"
    ) { state ->
        if (state) onColor else offColor
    }

    Canvas(
        modifier = modifier
            .graphicsLayer {
                translationY =
                    -(canvasLineSize.value + (size.height * (if (isChecked) TRANSLATION_Y_CHECKED_FACTOR else 0f)))
            }
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onCheckedChange(!isChecked) }
            .fillMaxSize()

    ) {
        val sizeMin = min(size.width, size.height)
        val switcherRadius = (sizeMin / 2f) - elevationPx

        val iconRadius = switcherRadius * ICON_RADIUS_FACTOR
        val iconClipRadius = iconRadius * ICON_CLIP_RADIUS_FACTOR
        val iconCollapsedWidth = (iconRadius - iconClipRadius) * ICON_COLLAPSED_WIDTH_SCALE
        val iconHeight = iconRadius * 2f

        val iconOffset = lerp(0f, iconRadius - iconCollapsedWidth / 2f, iconProgress)

        val baseIconLeft = (switcherRadius - iconCollapsedWidth / 2f - iconOffset) + elevationPx
        val baseIconRight = (switcherRadius + iconCollapsedWidth / 2f + iconOffset) + elevationPx
        val baseIconTop = ((switcherRadius * 2f - iconHeight) / 2f) + elevationPx / 2f
        val baseIconBottom =
            (switcherRadius * 2f - (switcherRadius * 2f - iconHeight) / 2f) + elevationPx / 2f

        var iconRect = Rect(baseIconLeft, baseIconTop, baseIconRight, baseIconBottom)
        val fixedIconProgress = 0f
        val fixedIconOffset = lerp(0f, iconRadius - iconCollapsedWidth / 2f, fixedIconProgress)
        val fixedIconLeft =
            (switcherRadius - iconCollapsedWidth / 2f - fixedIconOffset) + elevationPx
        val fixedIconRight =
            (switcherRadius + iconCollapsedWidth / 2f + fixedIconOffset) + elevationPx
        val fixedIconWidth = fixedIconRight - fixedIconLeft
        val lineSize = fixedIconWidth
        canvasLineSize.value = lineSize

        drawRoundRect(
            color = Color.White,
            topLeft = Offset(iconRect.left, iconRect.top),
            size = Size(iconRect.width, iconRect.height),
            cornerRadius = CornerRadius(
                switcherRadius * RECT_CORNER_FACTOR,
                switcherRadius * RECT_CORNER_FACTOR
            )
        )

        val lineAnimProgress = if (isChecked) {
            1f - iconProgress
        } else {
            iconProgress
        }

        val lineSqueezeFactor = if (isChecked) {
            val bouncePart = exp(-lineAnimProgress / BOUNCE_ANIM_AMPLITUDE_OUT) *
                    cos(ANIMATION_SPEED_EXIT * lineAnimProgress)
            1f - (bouncePart * LINE_SQUEEZE_BOUNCE_FACTOR)
        } else {
            1f
        }

        drawLine(
            color = Color.White,
            start = Offset(
                iconRect.left + (lineSize * LINE_START_X_FACTOR) + (((iconRect.size.width) / 2) * iconProgress),
                iconRect.bottom - (lineSize * LINE_START_X_FACTOR) - ((lineSize * LINE_START_X_FACTOR) * iconProgress)
            ),
            end = Offset(
                iconRect.left + (lineSize * LINE_END_X_FACTOR) + ((iconRect.size.width + lineSize) * iconProgress),
                iconRect.bottom + elevationPx * ELEVATION_LINE_FACTOR + switcherRadius * SWITCH_RADIUS_LINE_FACTOR - ((lineSize) * iconProgress)
            ),
            strokeWidth = lineSize * lineSqueezeFactor.toFloat(),
            cap = StrokeCap.Round
        )

        val clipOffset = lerp(0f, iconClipRadius, iconProgress)
        val iconCenter = Offset(iconRect.center.x, iconRect.center.y)
        val iconClipRect = Rect(
            iconCenter.x - clipOffset,
            iconCenter.y - clipOffset,
            iconCenter.x + clipOffset,
            iconCenter.y + clipOffset
        )

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