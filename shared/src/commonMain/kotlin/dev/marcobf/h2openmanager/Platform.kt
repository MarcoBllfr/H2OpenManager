package dev.marcobf.h2openmanager

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform