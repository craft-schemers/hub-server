package com.craftschemers.hub.minigame

enum class GameType {
    ONE_IN_THE_QUIVER
}

fun getGameTypeFromName(name: String) = when (name.lowercase()) {
    "oitq" -> GameType.ONE_IN_THE_QUIVER
    else -> null
}
