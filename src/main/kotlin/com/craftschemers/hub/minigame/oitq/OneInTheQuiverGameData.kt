package com.craftschemers.hub.minigame.oitq

import com.craftschemers.hub.HubPlayer
import com.craftschemers.hub.minigame.AbstractGameData
import com.craftschemers.hub.minigame.GameState
import com.craftschemers.hub.minigame.GameType
import com.craftschemers.hub.tasks.StartGameTask

class OneInTheQuiverGameData : AbstractGameData() {

    private val startGameTask = StartGameTask(this, OneInTheQuiver)

    override fun addPlayer(player: HubPlayer) {
        if (state == GameState.LOBBY && players.size < OneInTheQuiver.maxPlayers) {
            OneInTheQuiver.lobbyLocation?.let { player.player.teleport(it) } ?: return
            player.game = GameType.ONE_IN_THE_QUIVER
            players.add(player)

            if (players.size >= OneInTheQuiver.minPlayers) {
                startGameTask.start()
            }

        }
    }

    override fun startGame() {
        TODO("Not yet implemented")
    }
}
