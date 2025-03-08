package org.mejdi14.search.data.listener



abstract class LiquidSearchActionListener {
    open fun onTextChange(text: String) {
        // Default implementation: Do nothing
    }

    open fun  onStateChange(isActive: Boolean) {
        // Default implementation: Do nothing
    }

    open fun onCancelClicked() {
        // Default implementation: Do nothing
    }

    open fun onDragStart() {
        // Default implementation: Do nothing
    }
}

val defaultLiquidSearchActionListener = object : LiquidSearchActionListener() {
    override fun onTextChange(text: String) {
        // Default implementation: Do nothing
    }

    override fun onStateChange(isActive: Boolean) {
        // Default implementation: Do nothing
    }

    override fun onCancelClicked() {
        // Default implementation: Do nothing
    }

    override fun onDragStart() {
        // Default implementation: Do nothing
    }
}