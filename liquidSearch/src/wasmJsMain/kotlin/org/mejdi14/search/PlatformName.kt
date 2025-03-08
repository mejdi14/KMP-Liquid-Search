package org.mejdi14.search

import kotlinx.browser.window

actual val isDesktop: PlatformName = PlatformName.WEB

actual fun isMobileDevice(): Boolean {
    // "Mobi" is a common substring in mobile user agents.
    return window.navigator.userAgent.contains("Mobi")
}