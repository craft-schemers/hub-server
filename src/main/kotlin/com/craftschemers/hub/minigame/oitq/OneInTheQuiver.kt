package com.craftschemers.hub.minigame.oitq

import com.craftschemers.hub.HubPlayer
import com.craftschemers.hub.minigame.Lobby
import com.craftschemers.hub.minigame.GameType
import com.craftschemers.hub.minigame.Minigame

object OneInTheQuiver : Minigame(
    name = "oitq",
    displayName = "One In The Quiver",
    type = GameType.ONE_IN_THE_QUIVER,
    minPlayers = 1,
    maxPlayers = 16,
) {

    override fun handlePlayerLeave(player: HubPlayer) {
        if (player.game != GameType.ONE_IN_THE_QUIVER) return
        val lobby = lobbies.find { it.players.contains(player) } ?: return
        lobby.players.remove(player)
    }

    override fun createNewLobby(): Lobby {
        return OneInTheQuiverLobby()
    }

}
