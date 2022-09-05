package com.craftschemers.hub.managers

import com.craftschemers.hub.HubPlayer
import com.craftschemers.hub.minigame.GameType
import com.craftschemers.hub.minigame.oitq.OneInTheQuiver
import com.craftschemers.hub.minigame.sendErrorMessage

class HubMinigameManager {

    val minigames = mapOf(
        GameType.ONE_IN_THE_QUIVER to OneInTheQuiver
    )

    fun addPlayerToMinigame(player: HubPlayer, gameType: GameType) {
        // make sure a server is available
        val gameServer = minigames[gameType]

        if (gameServer != null) {
            if (gameServer.lobbyLocation == null) {
                player.sendErrorMessage("Game ${gameServer.displayName} does not have a lobby set up!")
            } else {
                gameServer.addPlayer(player)
                player.game = gameType
            }
        }

    }

    fun removePlayerFromMinigame(player: HubPlayer) {
        if (player.game != null) {
            minigames[player.game]?.handlePlayerLeave(player)
            player.game = null
        }
    }

}
