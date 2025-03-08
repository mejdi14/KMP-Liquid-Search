package org.mejdi14.search.data.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

interface LiquidSearchController {
    fun clearFocus()
}

internal class LiquidSearchControllerImpl : LiquidSearchController {
    var onResetSearch: (() -> Unit)? = null

    override fun clearFocus() {
        onResetSearch?.invoke()
    }
}

@Composable
fun rememberLiquidSearchController(): LiquidSearchController {
    return remember { LiquidSearchControllerImpl() }
}