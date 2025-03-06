package org.mejdi14.project

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform