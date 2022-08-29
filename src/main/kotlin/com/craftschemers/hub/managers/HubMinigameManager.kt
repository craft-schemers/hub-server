package com.craftschemers.hub.managers

import com.craftschemers.hub.HubPlayer
import com.craftschemers.hub.minigame.GameState
import com.craftschemers.hub.minigame.GameType
import com.craftschemers.hub.minigame.Minigame
import com.craftschemers.hub.minigame.OneInTheQuiver

class HubMinigameManager {

    val minigameServers = mutableMapOf<GameType, List<Minigame>>()

    fun addPlayerToMinigame(player: HubPlayer, gameType: GameType) {
        // make sure a server is available
        val gameServer = minigameServers[gameType]
        if (gameServer == null || gameServer.none { it.state == GameState.LOBBY }) {
            // make a new server
            val game = when (gameType) {
                GameType.ONE_IN_THE_QUIVER -> OneInTheQuiver()
            }
            game.addPlayer(player)
            minigameServers[gameType] = gameServer.orEmpty() + mutableListOf(game)
        } else {
            gameServer.find { it.state == GameState.LOBBY }?.addPlayer(player)
        }

        player.game = gameType

    }

    private fun getGamePlayerIsIn(player: HubPlayer): Minigame? =
        player.game?.let { gameType -> minigameServers[gameType]?.find { it.hasPlayer(player) } }

    fun removePlayerFromMinigame(player: HubPlayer) {
        val game = getGamePlayerIsIn(player) ?: return
        game.handlePlayerLeave(player)
        player.game = null
    }

}
