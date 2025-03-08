package org.mejdi14.project.data.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

interface LiquidSearchController {
    fun clearFocus()
}

private class LiquidSearchControllerImpl : LiquidSearchController {
    var onClearFocus: (() -> Unit)? = null

    override fun clearFocus() {
        onClearFocus?.invoke()
    }
}

@Composable
fun rememberLiquidSearchController(): LiquidSearchController {
    return remember { LiquidSearchControllerImpl() }
}