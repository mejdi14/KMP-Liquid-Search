package org.mejdi14.search

expect val isDesktop: PlatformName

enum class PlatformName{
    DESKTOP,
    WEB,
    MOBILE
}

expect fun isMobileDevice(): Boolean