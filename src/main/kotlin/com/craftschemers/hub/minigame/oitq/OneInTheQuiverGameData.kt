package com.craftschemers.hub.minigame.oitq

import com.craftschemers.hub.HubPlayer
import com.craftschemers.hub.minigame.AbstractGameData
import com.craftschemers.hub.minigame.GameState
import com.craftschemers.hub.minigame.GameType

class OneInTheQuiverGameData : AbstractGameData() {
    override fun addPlayer(player: HubPlayer) {
        if (state == GameState.LOBBY && players.size < OneInTheQuiver.maxPlayers) {
            OneInTheQuiver.lobbyLocation?.let { player.player.teleport(it) } ?: return
            player.game = GameType.ONE_IN_THE_QUIVER
            players.add(player)
        }
    }
}
